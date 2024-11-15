package com.example.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/library-books")
public class LibraryBookController {
    @Autowired
    private LibraryBookService libraryBookService;

    @GetMapping("/available")
    public ResponseEntity<List<LibraryBook>> getAvailableBooks() {
        return ResponseEntity.ok(libraryBookService.getAvailableBooks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryBook> updateBook(@PathVariable Long id, @RequestBody LibraryBook bookDetails) {
        try {
            return ResponseEntity.ok(libraryBookService.updateBook(id, bookDetails));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity addBook(@RequestBody LibraryBook book) {
        try {
            return ResponseEntity.ok(libraryBookService.addBook(book));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
