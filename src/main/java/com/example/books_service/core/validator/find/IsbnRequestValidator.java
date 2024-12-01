package com.example.books_service.core.validator.find;

import com.example.books_service.core.dto.request.FindBookByIsbnRequest;
import com.example.books_service.core.validator.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface IsbnRequestValidator {
    public Set<ValidationError> validate(FindBookByIsbnRequest request);
}
