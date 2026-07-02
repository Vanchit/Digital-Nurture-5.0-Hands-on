package com.library.repository;

public class BookRepository {

    public void addBook(String title) {
        System.out.println("BookRepository: Adding book -> " + title);
    }

    public void removeBook(String title) {
        System.out.println("BookRepository: Removing book -> " + title);
    }

    public void findBook(String title) {
        System.out.println("BookRepository: Finding book -> " + title);
    }
}
