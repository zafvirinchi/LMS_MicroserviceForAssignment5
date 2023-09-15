package com.tcs.author.controller;


import com.tcs.author.entity.Author;
import com.tcs.author.service.IAuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="author")
public class AuthorController {

    @Autowired
    IAuthorService authorService;

   
    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

   
    @PostMapping("/")
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    
    @DeleteMapping("/")
    public void deleleteAuthor(@RequestParam Long id) {
        authorService.deleteAuthor(id);
    }

    
    @PutMapping("/")
    public Author updateAuthor(@RequestBody Author author) {
        return authorService.updateAuthor(author);
    }

}
