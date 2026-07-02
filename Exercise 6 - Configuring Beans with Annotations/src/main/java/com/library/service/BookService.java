package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void addBook(String title) {
        bookRepository.addBook(title);
    }

    public void searchBook(String title) {
        bookRepository.findBook(title);
    }
}
