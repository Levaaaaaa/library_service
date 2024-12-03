package com.example.books_service.core.validator.update;

import com.example.books_service.core.dto.request.UpdateBookRequest;
import com.example.books_service.core.validator.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface UpdateRequestValidator {
    public Set<ValidationError> validate(UpdateBookRequest request);
}
