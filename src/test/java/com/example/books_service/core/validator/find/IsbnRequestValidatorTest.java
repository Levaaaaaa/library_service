package com.example.books_service.core.validator.find;

import com.example.books_service.core.dto.request.FindBookByIsbnRequest;
import com.example.books_service.core.validator.ValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IsbnRequestValidatorTest {
    @Autowired
    private IsbnRequestValidator validator;

    private FindBookByIsbnRequest request;

    @Test
    public void notNull() {
        assertNotNull(validator);
    }

    @Test
    public void correctISBN() {
        request = new FindBookByIsbnRequest("1234567891011");
        Set<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void IsbnIsNull() {
        request = new FindBookByIsbnRequest();
        Set<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("ISBN must not be empty", errors.stream().findFirst().get().getDescription());
    }

    @Test
    public void isbnIsEmpty() {
        request = new FindBookByIsbnRequest("");
        Set<ValidationError> errors = validator.validate(request);
        assertEquals(2, errors.size());
    }

    @Test
    public void IsbnIsTooSmall() {
        request = new FindBookByIsbnRequest("59999999");
        Set<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("ISBN can have only 13 digits", errors.stream().findFirst().get().getDescription());
    }

    @Test
    public void IsbnIsNoDigits() {
        request = new FindBookByIsbnRequest("abacababcdefg");
        Set<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("ISBN can have only 13 digits", errors.stream().findFirst().get().getDescription());
    }

    @Test
    public void IsbnTooLong() {
        request = new FindBookByIsbnRequest("123456789101112");
        Set<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("ISBN can have only 13 digits", errors.stream().findFirst().get().getDescription());
    }
}