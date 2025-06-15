package com.example.bookapi.controller;

import com.example.bookapi.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final List<Book> bookList = new ArrayList<>();

    // Global variable to track IDs
    private Long nextId = 1L;  // Use Long with "L" suffix

    // POST - Add book
    @PostMapping("/books")
    public ResponseEntity<Long> addBook(@RequestBody Book book) {
        book.setId(nextId++);
        bookList.add(book);
        return new ResponseEntity<>(book.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookList;
    }

    // ✅ Add this inside the class, not outside any brackets
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Optional<Book> book = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                return new ResponseEntity<>("Book updated successfully!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }

    // DELETE - Delete book by ID
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookList.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();

        if (optionalBook.isPresent()) {
            bookList.remove(optionalBook.get());
            return new ResponseEntity<>("Book deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }


}
