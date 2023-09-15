package com.tcs.author.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.tcs.author.model.Book;


@Entity(name="AUTHOR")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    @ElementCollection(targetClass=String.class)
    private List<String> bookIds;
    @Transient
    private List<Book> books;
}