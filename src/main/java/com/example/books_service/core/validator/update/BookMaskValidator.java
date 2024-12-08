package com.example.books_service.core.validator.update;

import com.example.books_service.core.dto.library.BookMask;
import com.example.books_service.core.validator.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface BookMaskValidator {
    public Set<ValidationError> validate(BookMask bookMask);
}
