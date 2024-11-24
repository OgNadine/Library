package com.example.LibraryService.mapper;

import com.example.LibraryService.dto.LibraryBookRequestDTO;
import com.example.LibraryService.dto.LibraryBookResponseDTO;
import com.example.LibraryService.entity.LibraryBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryBookMapper {
    LibraryBook toEntity(LibraryBookRequestDTO dto);
    LibraryBookResponseDTO toDto(LibraryBook entity);
}
