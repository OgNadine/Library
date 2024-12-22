package org.example.libraryservice.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LibraryBook {
    @Id
    private Long id;
    private LocalDateTime borrowedAt;
    private LocalDateTime dueAt;
    public LibraryBook(){

    }
    public LibraryBook(Long id, LocalDateTime borrowedAt, LocalDateTime dueAt) {
        this.id = id;
        this.borrowedAt = borrowedAt;
        this.dueAt = dueAt;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDateTime getDueAt() {
        return dueAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBorrowedAt(LocalDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public void setDueAt(LocalDateTime dueAt) {
        this.dueAt = dueAt;
    }
}
