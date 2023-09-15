package com.tcs.book.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.tcs.book.model.Author;

@Entity(name ="BOOK")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String ISBN;
    private Long authorId;
    @Transient
    private Author author;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
}

