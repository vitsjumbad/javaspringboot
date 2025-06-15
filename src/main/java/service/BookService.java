package com.example.bookapi.service;

import com.example.bookapi.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service  // Tells Spring this is a Service component
public class BookService {

    private final List<Book> books = new ArrayList<>();  // In-memory list

    public List<Book> getAllBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
