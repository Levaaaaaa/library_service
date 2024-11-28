package com.example.books_service.validator.book;

import com.example.books_service.core.dto.Book;
import com.example.books_service.validator.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface BookValidator {
    public Set<ValidationError<Book>> validate(Book book);
}
