package com.example.books_service.core.utils;

import com.example.books_service.core.dto.User;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface EntityConverter {
    public UserEntity toEntity(User user);
    public User fromEntity(UserEntity entity);
}
