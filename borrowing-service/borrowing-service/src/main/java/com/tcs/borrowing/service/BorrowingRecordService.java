package com.tcs.borrowing.service;


import com.tcs.borrowing.entity.BorrowingRecord;
import com.tcs.borrowing.exception.NoBookAvailable;
import com.tcs.borrowing.model.Book;
import com.tcs.borrowing.model.BookStatus;
import com.tcs.borrowing.repository.BorrowingRecordRepository;
import com.tcs.borrowing.utility.MessageSender;
import com.tcs.borrowing.utility.UtilityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service("borrowingRecordService")
public class BorrowingRecordService implements IBorrowingRecordService {

    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Value("${book.service.url}")
    String bookServiceURL;

    @Autowired
    MessageSender messageSender;
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public BorrowingRecord borrowBook(Book book, String user) throws RuntimeException {
        Book availableBook = null;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(bookServiceURL + "/" + book.getId(), String.class);
            availableBook = UtilityMapper.responseToModel(response.getBody());
        } catch (Exception e) {
            throw new NoBookAvailable("Book Service not up, Something happens! try again");
        }

        if (availableBook.getStatus().equals(BookStatus.AVAILABLE)) {
            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBorrowingDate(LocalDate.now());
            borrowingRecord.setReturnDate(LocalDate.now().minusWeeks(2));
            borrowingRecord.setUser(user);
            borrowingRecord.setBookId(availableBook.getId());
            borrowingRecordRepository.save(borrowingRecord);
            availableBook.setStatus(BookStatus.BORROWED);
            borrowingRecord.setBook(availableBook);
            messageSender.send(UtilityMapper.getJsonString(availableBook));
            return borrowingRecord;
        } else {
            throw new NoBookAvailable("Book Not available");
        }
    }

    @Override
    public List<BorrowingRecord> getBorrowByBookId(Long bookId) {
        return borrowingRecordRepository.getByBookId(bookId);
    }

}
