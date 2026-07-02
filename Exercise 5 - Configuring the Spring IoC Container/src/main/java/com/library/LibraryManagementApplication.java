package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("Exercise 5: Spring IoC Container Configuration");
        System.out.println("Loading ApplicationContext from applicationContext.xml...\n");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\nBeans registered in IoC Container:");
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("  -> " + name);
        }

        System.out.println();
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.addBook("Spring Framework in Action");
        bookService.searchBook("Clean Architecture");

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring IoC Container closed.");
    }
}
