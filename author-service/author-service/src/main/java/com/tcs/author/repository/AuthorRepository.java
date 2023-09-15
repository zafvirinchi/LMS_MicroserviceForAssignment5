package com.tcs.author.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.author.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
