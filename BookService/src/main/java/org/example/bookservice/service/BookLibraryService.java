package org.example.bookservice.service;

import org.example.bookservice.LibraryRecordRequest;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class BookLibraryService {
    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;

    @Autowired
    public BookLibraryService(RestTemplate restTemplate, EurekaClient eurekaClient) {
        this.restTemplate = restTemplate;
        this.eurekaClient = eurekaClient;
    }

    public void sendBookId(Long bookId) {
        String libraryServiceUrl = getLibraryServiceUrl();

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

    private String getLibraryServiceUrl() {
        String serviceId = "library-service"; // Имя сервиса, как оно зарегистрировано в Eureka
        String libraryServiceInstance = eurekaClient.getNextServerFromEureka(serviceId, false).getHomePageUrl();
        return libraryServiceInstance + "/api/library-books/register"; // Полный URL с эндпоинтом
    }
}
