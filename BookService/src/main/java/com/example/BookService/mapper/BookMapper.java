package com.example.BookService.mapper;

import com.example.BookService.dto.BookRequestDTO;
import com.example.BookService.dto.BookResponseDTO;
import com.example.BookService.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookRequestDTO dto);
    BookResponseDTO toDto(Book book);
}
