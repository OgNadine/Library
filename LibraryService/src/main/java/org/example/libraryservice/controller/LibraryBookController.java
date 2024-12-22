package org.example.libraryservice.controller;

import org.example.libraryservice.LibraryRecordRequest;
import org.example.libraryservice.dto.LibraryBookListResponseDTO;
import org.example.libraryservice.entity.LibraryBook;
import org.example.libraryservice.service.LibraryBookService;
import org.example.libraryservice.exception.ResourceNotFoundException;
import org.example.libraryservice.dto.LibraryBookRequestDTO;
import org.example.libraryservice.dto.LibraryBookResponseDTO;
import org.example.libraryservice.mapper.LibraryBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/library-books")
public class LibraryBookController {
    @Autowired
    private LibraryBookService libraryBookService;

    @Autowired
    private LibraryBookMapper libraryBookMapper;

    @GetMapping("/available")
    public ResponseEntity<LibraryBookListResponseDTO> getAvailableBooks() {
        List<LibraryBookResponseDTO> books = libraryBookService.getAvailableBooks().stream()
                .map(libraryBookMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new LibraryBookListResponseDTO(books), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryBookResponseDTO> updateBook(@PathVariable Long id, @RequestBody LibraryBookRequestDTO bookDetails) throws ResourceNotFoundException {
        LibraryBookResponseDTO updatedBook = libraryBookMapper.toDto(
                libraryBookService.updateBook(id, libraryBookMapper.toEntity(bookDetails))
        );
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LibraryBookResponseDTO> addBook(@RequestBody LibraryBookRequestDTO bookRequestDTO) {
        LibraryBookResponseDTO newBook = libraryBookMapper.toDto(
                libraryBookService.addBook(libraryBookMapper.toEntity(bookRequestDTO))
        );
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerBook(@RequestBody LibraryRecordRequest request) {
        libraryBookService.addLibraryBookRecord(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}