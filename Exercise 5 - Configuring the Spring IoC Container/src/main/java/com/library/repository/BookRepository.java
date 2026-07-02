package com.library.repository;

public class BookRepository {

    public void addBook(String title) {
        System.out.println("BookRepository [IoC]: Adding book -> " + title);
    }

    public void findBook(String title) {
        System.out.println("BookRepository [IoC]: Finding book -> " + title);
    }
}
