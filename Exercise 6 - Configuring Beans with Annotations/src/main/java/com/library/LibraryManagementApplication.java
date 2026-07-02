package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("Exercise 6: Annotation-Based Bean Configuration");
        System.out.println("Loading Spring Context with Component Scanning...\n");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Beans auto-detected via @Service / @Repository:\n");

        // BookService is auto-detected via @Service annotation
        BookService bookService = context.getBean(BookService.class);

        bookService.addBook("Effective Java");
        bookService.searchBook("Spring Boot in Practice");

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring Context closed.");
    }
}
