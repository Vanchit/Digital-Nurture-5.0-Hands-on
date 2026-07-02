package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("Exercise 7: Constructor and Setter Injection");
        System.out.println("Loading Spring Application Context...\n");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // Test Constructor Injection
        System.out.println("\n--- Testing Constructor Injection ---");
        BookService constructorService =
                (BookService) context.getBean("bookServiceConstructor");
        constructorService.addBook("Effective Java");
        constructorService.searchBook("Design Patterns");

        // Test Setter Injection
        System.out.println("\n--- Testing Setter Injection ---");
        BookService setterService =
                (BookService) context.getBean("bookServiceSetter");
        setterService.addBook("Clean Code");
        setterService.searchBook("Refactoring");

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring Context closed.");
    }
}
