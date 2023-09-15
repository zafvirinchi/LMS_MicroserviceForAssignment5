package com.tcs.author.service;


import com.tcs.author.entity.Author;
import com.tcs.author.model.Book;
import com.tcs.author.repository.AuthorRepository;
import com.tcs.author.utility.UtilityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("authorService")
public class AuthorService implements IAuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Value("${book.service.url}")
	String bookServiceURL;

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public Author getAuthor(Long id) {
		// TODO Auto-generated method stub
		Optional<Author> author = authorRepository.findById(id);
		Author result = null;
		if(author.isPresent()) {
			result = author.get();
			ResponseEntity<String> response = restTemplate.getForEntity(bookServiceURL + "/author/" + result.getId(), String.class);
			List<Book> books = (List<Book>)UtilityMapper.responseToModel(response.getBody(), Book.class);
			result.setBooks(books);
		}
		return result;
	}

	@Override
	public Author createAuthor(Author author) {
		// TODO Auto-generated method stub
		List<String> bookIds = new ArrayList<>();
		if(author.getBooks() != null) {
			author.getBooks().forEach(book -> bookIds.add(book.getId().toString()));
			author.setBookIds(bookIds);
		}
		return authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(Long id) {
		// TODO Auto-generated method stub
		 authorRepository.deleteById(id);;
	}

	@Override
	public Author updateAuthor(Author author) {
		// TODO Auto-generated method stub
		Optional<Author> authorDb = authorRepository.findById(author.getId());
        authorDb.ifPresent(value -> author.setId(value.getId()));
		return authorRepository.save(author);
	}

}
