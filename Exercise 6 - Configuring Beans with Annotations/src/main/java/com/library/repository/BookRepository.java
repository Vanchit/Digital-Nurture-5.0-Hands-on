package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public void addBook(String title) {
        System.out.println("BookRepository [@Repository]: Adding book -> " + title);
    }

    public void findBook(String title) {
        System.out.println("BookRepository [@Repository]: Finding book -> " + title);
    }
}
