package com.example.books_service.core.utils;

import com.example.books_service.core.dto.User;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.domain.UserEntity;
import com.example.books_service.core.model.repos.AuthorRepository;
import com.example.books_service.core.model.repos.GenreRepository;
import com.example.books_service.core.model.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class EntityConverterImpl implements EntityConverter{
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository findGenreRepository;

    @Autowired
    private UserRepository userRepository;

    public User fromEntity(UserEntity entity) {
        return new User(entity.getUsername(), entity.getPassword());
    }

    public UserEntity toEntity(User user) {
        Optional<UserEntity> optional = userRepository.findByUsername(user.getUsername());
        if (optional.isPresent()) {
            return optional.get();
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        return entity;
    }
}
