package com.example.books_service.core.validator;

import com.example.books_service.core.dto.library.Author;
import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.dto.library.request.AddBookRequest;
import com.example.books_service.core.validator.author.AuthorValidator;
import com.example.books_service.core.validator.book.BookValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class BookAndAuthorValidatorImpl implements BookAndAuthorValidator {
    @Autowired
    private BookValidator bookValidator;

    @Autowired
    private AuthorValidator authorValidator;

    @Override
    public Set<ValidationError> validate(AddBookRequest request) {

            Set<ValidationError> result = validateRequest(request);
            //validate book
            if (request != null && request.getBook() != null) {
                Book book = request.getBook();
                Set<ValidationError> bookResult = bookValidator.validate(book);
                result.addAll(bookResult);

                //validate author
                if (book.getAuthor() != null) {
                    Author author = book.getAuthor();
                    Set<ValidationError> authorResult = authorValidator.validate(author);
                    result.addAll(authorResult);
                }
            }
            return result;
    }

    private Set<ValidationError> validateRequest(AddBookRequest request) {
        try (ValidatorFactory vf = Validation.buildDefaultValidatorFactory()) {
            Validator validator = vf.getValidator();
            Set<ConstraintViolation<AddBookRequest>> violations = validator.validate(request);
            return violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .map(ValidationError::new)
                    .collect(Collectors.toSet());
        }
    }
}
