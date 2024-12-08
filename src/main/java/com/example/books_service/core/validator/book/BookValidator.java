package com.example.books_service.core.validator.book;

import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.validator.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface BookValidator {
    public Set<ValidationError> validate(Book book);
}
