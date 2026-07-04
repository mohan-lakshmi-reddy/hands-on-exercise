package com.library;

import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class LibraryManagementApplication {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");
        BookRepository injectedRepository = bookService.getBookRepository();
        if (injectedRepository != null) {
            System.out.println("Dependency Injection successful: BookRepository was injected into BookService.");
        } else {
            System.out.println("Dependency Injection FAILED: BookRepository is null.");
        }

        System.out.println();
        bookService.listAllBooks();

        System.out.println();
        bookService.addNewBook("Head First Design Patterns");

        System.out.println();
        bookService.listAllBooks();
    }
}
