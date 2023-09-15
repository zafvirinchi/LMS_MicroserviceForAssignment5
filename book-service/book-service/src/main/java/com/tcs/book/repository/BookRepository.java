package com.tcs.book.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.book.entity.Book;
import com.tcs.book.entity.BookStatus;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> getByIdAndStatus(Long id, BookStatus status);

    List<Book> getBooksByAuthorId(Long authorId);
}
