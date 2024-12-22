package org.example.libraryservice.repository;

import org.example.libraryservice.entity.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
    List<LibraryBook> findByDueAtIsNull();
}
