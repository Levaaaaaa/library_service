package com.example.books_service.core.validator.author;

import com.example.books_service.core.dto.library.Author;
import com.example.books_service.core.validator.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface AuthorValidator {
    public Set<ValidationError> validate(Author author);
}
