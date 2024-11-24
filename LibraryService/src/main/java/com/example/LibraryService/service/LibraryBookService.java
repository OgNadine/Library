package com.example.LibraryService.service;

import com.example.LibraryService.LibraryRecordRequest;
import com.example.LibraryService.constants.ErrorMessages;
import com.example.LibraryService.entity.LibraryBook;
import com.example.LibraryService.exception.ResourceNotFoundException;
import com.example.LibraryService.repository.LibraryBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LibraryBookService {
    @Autowired
    private LibraryBookRepository libraryBookRepository;

    public void addLibraryBookRecord(LibraryRecordRequest request) {
        LibraryBook libraryBook = new LibraryBook();
        libraryBook.setId(request.getBookId());
        libraryBook.setBorrowedAt(request.getBorrowedAt());
        libraryBook.setDueAt(request.getDueAt());
        libraryBookRepository.save(libraryBook);
    }

    public List<LibraryBook> getAvailableBooks() {
        return libraryBookRepository.findByDueAtIsNull();
    }

    public LibraryBook updateBook(Long id, LibraryBook bookDetails) throws ResourceNotFoundException {
        LibraryBook book = libraryBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.BOOK_NOT_FOUND, id)));
        book.setBorrowedAt(bookDetails.getBorrowedAt());
        book.setDueAt(bookDetails.getDueAt());
        return libraryBookRepository.save(book);
    }

    public LibraryBook addBook(LibraryBook book) {
        return libraryBookRepository.save(book);
    }
}


