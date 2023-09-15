package com.tcs.borrowing.service;

import com.tcs.borrowing.entity.BorrowingRecord;
import com.tcs.borrowing.model.Book;

import java.util.List;

public interface IBorrowingRecordService {

    BorrowingRecord borrowBook(Book book, String user) throws RuntimeException;

    List<BorrowingRecord> getBorrowByBookId(Long bookId);

}
