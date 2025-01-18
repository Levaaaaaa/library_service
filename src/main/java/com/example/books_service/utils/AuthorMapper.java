package com.example.books_service.utils;

import com.example.books_service.dto.AuthorDTO;
import com.example.books_service.entities.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDTO(AuthorEntity entity);
    AuthorEntity toEntity(AuthorDTO dto);
}
