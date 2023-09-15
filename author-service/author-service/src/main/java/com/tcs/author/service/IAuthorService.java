package com.tcs.author.service;


import com.tcs.author.entity.Author;

public interface IAuthorService {

	Author getAuthor(Long id);

	Author createAuthor(Author author);

	void deleteAuthor(Long id);

	Author updateAuthor(Author author);

}
