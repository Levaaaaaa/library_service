package com.example.books_service.utils;

import com.example.books_service.dto.BookDTO;
import com.example.books_service.entities.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(BookEntity entity);
    BookEntity toEntity(BookDTO dto);
}
