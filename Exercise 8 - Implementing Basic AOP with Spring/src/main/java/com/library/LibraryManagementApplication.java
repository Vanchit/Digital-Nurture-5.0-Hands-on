package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("Exercise 8: Basic AOP with Spring (@Before / @After Advice)");
        System.out.println("Loading Spring Application Context...\n");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");

        System.out.println("========================================");
        bookService.addBook("Effective Java");
        System.out.println("========================================");
        bookService.searchBook("Clean Code");
        System.out.println("========================================");
        bookService.removeBook("Design Patterns");
        System.out.println("========================================");

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring Context closed.");
    }
}
