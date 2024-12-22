package org.example.bookservice;

import java.time.LocalDateTime;

public class LibraryRecordRequest {
    private Long bookId;
    private LocalDateTime borrowedAt;
    private LocalDateTime dueAt;

    public LibraryRecordRequest(Long bookId, LocalDateTime borrowedAt, LocalDateTime dueAt) {
        this.bookId = bookId;
        this.borrowedAt = borrowedAt;
        this.dueAt = dueAt;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public LocalDateTime getDueAt() {
        return dueAt;
    }

    public void setDueAt(LocalDateTime dueAt) {
        this.dueAt = dueAt;
    }
}
