package com.example.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void sendBookIdToLibraryService(Long bookId) {
        String libraryServiceUrl = "http://localhost:8081/library-service/api/library-books";
        LibraryRecordRequest request = new LibraryRecordRequest(bookId, LocalDateTime.now(), LocalDateTime.now().plusDays(30));
        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(libraryServiceUrl, request, Void.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to send book ID to LibraryService: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Error while sending book ID: " + e.getMessage());
            throw new RuntimeException("Error while communicating with LibraryService", e);
        }
    }
    public Book addBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book updateBook(Long id, Book bookDetails) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setIsbn(bookDetails.getIsbn());
        book.setTitle(bookDetails.getTitle());
        book.setGenre(bookDetails.getGenre());
        book.setDescription(bookDetails.getDescription());
        book.setAuthor(bookDetails.getAuthor());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.delete(book);
    }
}
