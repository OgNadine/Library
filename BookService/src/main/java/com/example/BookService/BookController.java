package com.example.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity getAllBooks() {
        try {
            return ResponseEntity.ok(bookService.getAllBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) throws ResourceNotFoundException {
        Book book = bookService.getBookById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return ResponseEntity.ok(book);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) throws ResourceNotFoundException {
        Book book = bookService.getBookByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity addBook(@RequestBody Book book) {
        try {
            return ResponseEntity.ok(bookService.addBook(book));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody Book bookDetails) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) throws ResourceNotFoundException {
        bookService.deleteBook(id);
        try {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

