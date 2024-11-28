package com.example.books_service.validator.author;

import com.example.books_service.core.dto.Author;
import com.example.books_service.validator.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface AuthorValidator {
    public Set<ValidationError<Author>> validation(Author author);
}
