package com.tcs.book.service;


import com.tcs.book.entity.Book;
import com.tcs.book.entity.BookStatus;
import com.tcs.book.model.BorrowingRecord;
import com.tcs.book.repository.BookRepository;
import com.tcs.book.utility.UtilityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("bookService")
public class BookService implements IBookService {

    @Autowired
    BookRepository bookRepository;
    @Value("${borrowing.service.url}")
    String borrowingServiceURL;
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Book getBook(Long id) {
        // TODO Auto-generated method stub
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public Book createBook(Book book) {
        // TODO Auto-generated method stub
        book.setAuthorId(book.getAuthor().getId());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        // TODO Auto-generated method stub
        ResponseEntity<String> response = restTemplate.getForEntity(borrowingServiceURL + "/book/" + id, String.class);
        List<BorrowingRecord> borrowingRecords = (List<BorrowingRecord>) UtilityMapper.responseToModel(response.getBody(), BorrowingRecord.class);
        if (borrowingRecords.isEmpty()) {
            bookRepository.deleteById(id);
        } else {
            Optional<Book> book = bookRepository.findById(id);
            book.ifPresent(value -> {
                if (!value.getStatus().equals(BookStatus.MARK_DELETED)) {
                    value.setStatus(BookStatus.MARK_DELETED);
                    bookRepository.save(value);
                }
            });
        }
    }

    @Override
    public Book updateBook(Book book) {
        // TODO Auto-generated method stub
        Optional<Book> bookDb = bookRepository.findById(book.getId());
        bookDb.ifPresent(value -> book.setId(value.getId()));
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooksByAutherId(Long authorId) {
        return bookRepository.getBooksByAuthorId(authorId);
    }

}
