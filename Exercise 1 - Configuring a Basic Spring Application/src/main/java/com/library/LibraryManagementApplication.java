package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("Loading Spring Application Context...");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Spring Context loaded successfully!");

        BookService bookService = (BookService) context.getBean("bookService");
        bookService.registerBook("Effective Java");

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("Spring Context closed.");
    }
}
