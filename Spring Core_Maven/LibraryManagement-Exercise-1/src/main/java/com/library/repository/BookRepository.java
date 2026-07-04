package com.library.repository;

import java.util.ArrayList;
import java.util.List;


public class BookRepository {

    private final List<String> books = new ArrayList<>();

    public BookRepository() {
        books.add("Effective Java");
        books.add("Clean Code");
        books.add("Spring in Action");
    }

    public List<String> findAllBooks() {
        return books;
    }

    public void addBook(String title) {
        books.add(title);
    }
}
