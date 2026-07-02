package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // Setter for Spring IoC injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: BookRepository set by Spring IoC Container.");
    }

    public void addBook(String title) {
        bookRepository.addBook(title);
    }

    public void searchBook(String title) {
        bookRepository.findBook(title);
    }
}
