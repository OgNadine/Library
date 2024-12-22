package org.example.bookservice.dto;

import java.util.List;

public class BookListResponseDTO {
    private List<BookResponseDTO> books;

    public BookListResponseDTO(List<BookResponseDTO> books) {
        this.books = books;
    }

    public List<BookResponseDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponseDTO> books) {
        this.books = books;
    }
}
