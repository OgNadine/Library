package com.example.BookService.controller;

import com.example.BookService.dto.BookListResponseDTO;
import com.example.BookService.dto.BookRequestDTO;
import com.example.BookService.dto.BookResponseDTO;
import com.example.BookService.mapper.BookMapper;
import com.example.BookService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public BookListResponseDTO getAllBooks() {
        List<BookResponseDTO> bookDtos = bookService.getAllBooks().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
        return new BookListResponseDTO(bookDtos);
    }

    @GetMapping("/{id}")
    public BookResponseDTO getBookById(@PathVariable Long id) {
        return bookMapper.toDto(bookService.getBookById(id));
    }

    @GetMapping("/isbn/{isbn}")
    public BookResponseDTO getBookByIsbn(@PathVariable String isbn) {
        return bookMapper.toDto(bookService.getBookByIsbn(isbn));
    }

    @PostMapping
    public BookResponseDTO addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookMapper.toDto(bookService.addBook(bookMapper.toEntity(bookRequestDTO)));
    }

    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        return bookMapper.toDto(bookService.updateBook(id, bookMapper.toEntity(bookRequestDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
