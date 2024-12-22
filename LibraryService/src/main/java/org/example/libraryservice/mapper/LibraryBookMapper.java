package org.example.libraryservice.mapper;


import org.example.libraryservice.dto.LibraryBookRequestDTO;
import org.example.libraryservice.dto.LibraryBookResponseDTO;
import org.example.libraryservice.entity.LibraryBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryBookMapper {
    LibraryBook toEntity(LibraryBookRequestDTO dto);
    LibraryBookResponseDTO toDto(LibraryBook entity);
}
