package org.example.bookservice.mapper;


import org.example.bookservice.entity.Book;
import org.example.bookservice.dto.BookRequestDTO;
import org.example.bookservice.dto.BookResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookRequestDTO dto);
    BookResponseDTO toDto(Book book);
}
