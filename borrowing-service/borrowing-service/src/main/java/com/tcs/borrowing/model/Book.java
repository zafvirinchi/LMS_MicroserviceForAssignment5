package com.tcs.borrowing.model;

import lombok.Data;


@Data
public class Book {
    private Long id;
    private String title;
    private String ISBN;
    private BookStatus status;
}

