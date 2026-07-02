package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("Exercise 4: Maven Project with Spring Context, AOP, WebMVC");
        System.out.println("Loading Spring Application Context...\n");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");
        bookService.addBook("Spring in Action");

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring Context closed.");
    }
}
