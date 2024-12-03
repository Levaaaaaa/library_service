package com.example.books_service.core.utils;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.builder.AuthorBuilder;
import com.example.books_service.core.dto.builder.BookBuilder;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface EntityConverter {
    public  Author fromEntity(AuthorEntity entity);
    public Book fromEntity(BookEntity entity);
    public String fromEntity(GenreEntity genre);

    public BookEntity toEntity(Book book);

    public AuthorEntity toEntity(Author author);

    public GenreEntity toEntity(String genre);
}
