package com.example.LibraryService.controller;

import com.example.LibraryService.LibraryRecordRequest;
import com.example.LibraryService.dto.LibraryBookListResponseDTO;
import com.example.LibraryService.dto.LibraryBookRequestDTO;
import com.example.LibraryService.dto.LibraryBookResponseDTO;
import com.example.LibraryService.entity.LibraryBook;
import com.example.LibraryService.exception.ResourceNotFoundException;
import com.example.LibraryService.mapper.LibraryBookMapper;
import com.example.LibraryService.service.LibraryBookService;
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
    public LibraryBookListResponseDTO getAvailableBooks() {
        List<LibraryBookResponseDTO> books = libraryBookService.getAvailableBooks().stream()
                .map(libraryBookMapper::toDto) .collect(Collectors.toList());
        return new LibraryBookListResponseDTO(books);
    }

    @PutMapping("/{id}")
    public LibraryBookResponseDTO updateBook(@PathVariable Long id, @RequestBody LibraryBookRequestDTO bookDetails) throws ResourceNotFoundException {
        return libraryBookMapper.toDto(
                libraryBookService.updateBook(id, libraryBookMapper.toEntity(bookDetails))
        );
    }

    @PostMapping
    public LibraryBookResponseDTO addBook(@RequestBody LibraryBookRequestDTO bookRequestDTO) {
        return libraryBookMapper.toDto(
                libraryBookService.addBook(libraryBookMapper.toEntity(bookRequestDTO))
        );
    }
    @PostMapping("/register")
    public ResponseEntity<Void> registerBook(@RequestBody LibraryRecordRequest request) {
        libraryBookService.addLibraryBookRecord(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}