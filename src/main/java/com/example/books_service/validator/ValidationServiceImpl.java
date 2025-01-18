package com.example.books_service.validator;

import com.example.books_service.entities.AuthorEntity;
import com.example.books_service.entities.BookEntity;
import com.example.books_service.entities.GenreEntity;
import com.example.books_service.utils.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
class ValidationServiceImpl implements ValidationService{
    @Autowired
    private Validator validator;

    @Override
    public boolean isValidBook(BookEntity book) throws ValidationException {
        List<ValidationError> errors = buildErrorsList(validator.validate(book));
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        return true;
    }

    @Override
    public boolean isValidAuthor(AuthorEntity author) throws ValidationException {
        List<ValidationError> errors = buildErrorsList(validator.validate(author));
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        return true;
    }

    @Override
    public boolean isValidGenre(GenreEntity genre) throws ValidationException {
        List<ValidationError> errors = buildErrorsList(validator.validate(genre));
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        return true;
    }

    @Override
    public boolean isValidBooksList(List<BookEntity> books) throws ValidationException {
        List<ValidationError> errors = books.stream()
                .map(validator::validate)
                .map(this::buildErrorsList)
                .flatMap(List::stream)
                .toList();
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        return true;
    }

    private <T>List<ValidationError> buildErrorsList(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .map(ValidationError::new)
                .toList();
    }
}
