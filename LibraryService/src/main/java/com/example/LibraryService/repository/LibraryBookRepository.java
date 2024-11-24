package com.example.LibraryService.repository;
import com.example.LibraryService.entity.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
    List<LibraryBook> findByDueAtIsNull();
}

