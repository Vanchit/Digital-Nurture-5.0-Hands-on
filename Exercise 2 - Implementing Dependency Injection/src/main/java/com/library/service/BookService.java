package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    // Dependency to be injected by Spring
    private BookRepository bookRepository;

    // Setter method for Spring Setter Injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: BookRepository injected via setter.");
    }

    public void addBook(String title) {
        bookRepository.addBook(title);
    }

    public void removeBook(String title) {
        bookRepository.removeBook(title);
    }

    public void searchBook(String title) {
        bookRepository.findBook(title);
    }
}
