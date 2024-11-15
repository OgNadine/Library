package com.example.BookService;

import java.time.LocalDateTime;

public class LibraryRecordRequest {
    private Long bookId;

    public LibraryRecordRequest(Long bookId, LocalDateTime now, LocalDateTime localDateTime) {
        this.bookId = bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }
}
