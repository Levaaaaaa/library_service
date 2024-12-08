package com.example.books_service.core.validator.find;

import com.example.books_service.core.dto.library.request.FindBookByIdRequest;
import com.example.books_service.core.validator.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface IdValidator {
    public Set<ValidationError> validate(FindBookByIdRequest request);
}
