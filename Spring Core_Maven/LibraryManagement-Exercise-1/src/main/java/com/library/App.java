package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");

        bookService.listAllBooks();

        System.out.println();
        bookService.addNewBook("Head First Design Patterns");

        System.out.println();
        bookService.listAllBooks();
    }
}
