package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // Constructor Injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: BookRepository injected via CONSTRUCTOR.");
    }

    // No-arg constructor needed for Setter Injection
    public BookService() {
        System.out.println("BookService: Created with no-arg constructor.");
    }

    // Setter Injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: BookRepository injected via SETTER.");
    }

    public void addBook(String title) {
        bookRepository.addBook(title);
    }

    public void searchBook(String title) {
        bookRepository.findBook(title);
    }
}
