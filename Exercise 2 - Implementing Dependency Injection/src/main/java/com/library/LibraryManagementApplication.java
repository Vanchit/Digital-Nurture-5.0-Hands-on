package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("Loading Spring Application Context...");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Spring Context loaded successfully!\n");

        // Retrieve BookService bean (BookRepository is injected via setter)
        BookService bookService = (BookService) context.getBean("bookService");

        bookService.addBook("Effective Java");
        bookService.searchBook("Clean Code");
        bookService.removeBook("Design Patterns");

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring Context closed.");
    }
}
