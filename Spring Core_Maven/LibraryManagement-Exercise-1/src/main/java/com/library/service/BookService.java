package com.library.service;

import com.library.repository.BookRepository;
import java.util.List;


public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
