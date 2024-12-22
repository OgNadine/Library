package org.example.bookservice.service;

import org.example.bookservice.constants.ErrorMessages;
import org.example.bookservice.dto.BookRequestDTO;
import org.example.bookservice.dto.BookResponseDTO;
import org.example.bookservice.entity.Book;
import org.example.bookservice.exception.ResourceNotFoundException;
import org.example.bookservice.mapper.BookMapper;
import org.example.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookLibraryService libraryService;

    @Autowired
    private BookMapper bookMapper;

    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
        Book book = bookMapper.toEntity(bookRequestDTO);
        Book savedBook = bookRepository.save(book);
        libraryService.sendBookId(savedBook.getId());
        return bookMapper.toDto(savedBook);
    }

    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookResponseDTO getBookById(Long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.BOOK_NOT_FOUND, id)));
        return bookMapper.toDto(book);
    }

    public BookResponseDTO getBookByIsbn(String isbn) throws ResourceNotFoundException {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.ISBN_NOT_FOUND, isbn)));
        return bookMapper.toDto(book);
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.BOOK_NOT_FOUND, id)));

        book.setIsbn(bookRequestDTO.getIsbn());
        book.setTitle(bookRequestDTO.getTitle());
        book.setGenre(bookRequestDTO.getGenre());
        book.setDescription(bookRequestDTO.getDescription());
        book.setAuthor(bookRequestDTO.getAuthor());

        return bookMapper.toDto(bookRepository.save(book));
    }

    public void deleteBook(Long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.BOOK_NOT_FOUND, id)));
        bookRepository.delete(book);
    }
}