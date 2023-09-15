package com.tcs.borrowing.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.borrowing.entity.BorrowingRecord;

import java.util.List;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    List<BorrowingRecord> getByBookId(Long bookId);

}
