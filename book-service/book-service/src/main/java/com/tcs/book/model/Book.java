package com.tcs.book.model;

import com.tcs.book.entity.BookStatus;

import lombok.Data;


@Data
public class Book {
    private Long id;
    private String title;
    private String ISBN;
    private BookStatus status;
}

