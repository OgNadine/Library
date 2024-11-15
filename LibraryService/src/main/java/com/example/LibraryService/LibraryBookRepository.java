package com.example.LibraryService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
    List<LibraryBook> findByDueAtIsNull();
}

