package com.library.service;

import com.library.repository.BookRepository;
import java.util.List;


public class BookService {

    private BookRepository bookRepository;


    public BookService() {
    }
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void listAllBooks() {
        List<String> books = bookRepository.findAllBooks();
        System.out.println("Books currently in the library:");
        for (String title : books) {
            System.out.println(" - " + title);
        }
    }

    public void addNewBook(String title) {
        bookRepository.addBook(title);
        System.out.println("Added: " + title);
    }
}
