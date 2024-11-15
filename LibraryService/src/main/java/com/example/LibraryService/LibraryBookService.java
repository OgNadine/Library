package com.example.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class LibraryBookService {
    @Autowired
    private LibraryBookRepository libraryBookRepository;

    public List<LibraryBook> getAvailableBooks() {
        return libraryBookRepository.findByDueAtIsNull();
    }

    public LibraryBook updateBook(Long id, LibraryBook bookDetails) throws ResourceNotFoundException {
        LibraryBook book = libraryBookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setBorrowedAt(bookDetails.getBorrowedAt());
        book.setDueAt(bookDetails.getDueAt());
        return libraryBookRepository.save(book);
    }

    public LibraryBook addBook(LibraryBook book) {
        LibraryBook savedBook = libraryBookRepository.save(book);
        return savedBook;
    }
}

