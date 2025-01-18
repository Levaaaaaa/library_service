package com.example.books_service.utils;

import com.example.books_service.dto.User;
import com.example.books_service.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface EntityConverter {
    public UserEntity toEntity(User user);
    public User fromEntity(UserEntity entity);
}
