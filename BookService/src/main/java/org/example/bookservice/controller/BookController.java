package org.example.bookservice.controller;

import org.example.bookservice.dto.BookListResponseDTO;
import org.example.bookservice.dto.BookRequestDTO;
import org.example.bookservice.dto.BookResponseDTO;
import org.example.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BookListResponseDTO> getAllBooks() {
        List<BookResponseDTO> bookDtos = bookService.getAllBooks();
        return new ResponseEntity<>(new BookListResponseDTO(bookDtos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        BookResponseDTO bookDto = bookService.getBookById(id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable String isbn) {
        BookResponseDTO bookDto = bookService.getBookByIsbn(isbn);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO bookDto = bookService.addBook(bookRequestDTO);
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO bookDto = bookService.updateBook(id, bookRequestDTO);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}