package org.example.libraryservice.dto;

import java.util.List;

public class LibraryBookListResponseDTO {
    private List<LibraryBookResponseDTO> books;

    public LibraryBookListResponseDTO(List<LibraryBookResponseDTO> books) {
        this.books = books;
    }

    public List<LibraryBookResponseDTO> getBooks() {
        return books;
    }

    public void setBooks(List<LibraryBookResponseDTO> books) {
        this.books = books;
    }
}
