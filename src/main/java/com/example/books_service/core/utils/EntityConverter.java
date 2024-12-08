package com.example.books_service.core.utils;

import com.example.books_service.core.dto.User;
import com.example.books_service.core.dto.library.Author;
import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface EntityConverter {
    public User fromEntity(UserEntity entity);
    public UserEntity toEntity(User user);
    public  Author fromEntity(AuthorEntity entity);
    public Book fromEntity(BookEntity entity);
    public String fromEntity(GenreEntity genre);

    public BookEntity toEntity(Book book);

    public AuthorEntity toEntity(Author author);

    public GenreEntity toEntity(String genre);
}
