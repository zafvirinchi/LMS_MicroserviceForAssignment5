package com.tcs.author.model;

import com.tcs.author.entity.Author;

import lombok.Data;


@Data
public class Book {
    private Long id;
    private String title;
    private String ISBN;
    private BookStatus status;
}

