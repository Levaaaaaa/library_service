package com.example.books_service.utils;

import com.example.books_service.dto.GenreDTO;
import com.example.books_service.entities.GenreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDTO toDTO(GenreEntity entity);
    GenreEntity toEntity(GenreDTO dto);
}
