package com.tcs.book.service;


import java.util.List;

import com.tcs.book.entity.Book;

public interface IBookService {

	Book getBook(Long id);

	Book createBook(Book book);

	void deleteBook(Long id);

	Book updateBook(Book book);

	List<Book> getBooksByAutherId(Long autherId);
}
