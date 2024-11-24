package com.example.BookService.service;

import com.example.BookService.constants.ErrorMessages;
import com.example.BookService.entity.Book;
import com.example.BookService.exception.ResourceNotFoundException;
import com.example.BookService.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookLibraryService libraryService;

    public Book addBook(Book book) {
        Book savedBook = bookRepository.save(book);
        libraryService.sendBookId(savedBook.getId());
        return savedBook;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) throws ResourceNotFoundException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.BOOK_NOT_FOUND, id)));
    }

    public Book getBookByIsbn(String isbn) throws ResourceNotFoundException {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.ISBN_NOT_FOUND, isbn)));
    }

    public Book updateBook(Long id, Book bookDetails) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.BOOK_NOT_FOUND, id)));
        book.setIsbn(bookDetails.getIsbn());
        book.setTitle(bookDetails.getTitle());
        book.setGenre(bookDetails.getGenre());
        book.setDescription(bookDetails.getDescription());
        book.setAuthor(bookDetails.getAuthor());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.BOOK_NOT_FOUND, id)));
        bookRepository.delete(book);
    }
}
