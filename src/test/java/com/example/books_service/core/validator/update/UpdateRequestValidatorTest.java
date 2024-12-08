package com.example.books_service.core.validator.update;

import com.example.books_service.core.dto.library.BookMask;
import com.example.books_service.core.dto.library.request.IsbnRequest;
import com.example.books_service.core.dto.library.request.UpdateBookRequest;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.find.IsbnRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UpdateRequestValidatorTest {
    @InjectMocks
    private UpdateRequestValidatorImpl validator;

    @Mock
    private BookMaskValidator bookMaskValidator;

    @Mock
    private IsbnRequestValidator isbnValidator;

    private BookMask bookMask = new BookMask();
    private IsbnRequest isbn = new IsbnRequest();
    private UpdateBookRequest request = new UpdateBookRequest();

    @Test
    public void notNull() {
        assertNotNull(validator);
    }

    //correct request
    @Test
    public void correctRequest() {
        request.setBookMask(bookMask);
        request.setIsbn(isbn);

        when(bookMaskValidator.validate(bookMask)).thenReturn(Set.of());
        when(isbnValidator.validate(isbn)).thenReturn(Set.of());

        assertTrue(validator.validate(request).isEmpty());
    }

    //request is null
    @Test
    public void requestIsNullTest() {
        assertEquals(1, validator.validate(null).size());
    }

    //isbn is null
    @Test
    public void isbnIsNull() {
        request.setBookMask(bookMask);
        when(bookMaskValidator.validate(bookMask)).thenReturn(Set.of());

        Collection<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("ISBN must not be null", errors.stream().findFirst().get().getDescription());
    }

    //mask is null
    @Test
    public void maskIsNull() {
        request.setIsbn(isbn);
        when(isbnValidator.validate(isbn)).thenReturn(Set.of());

        Collection<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Updated book must not be null", errors.stream().findFirst().get().getDescription());
    }

    //invalid ISBN
    @Test
    public void invalidIsbn() {
        request.setBookMask(bookMask);
        request.setIsbn(isbn);
        String expectedError = "ISBN ERROR";
        when(isbnValidator.validate(isbn)).thenReturn(Set.of(new ValidationError(expectedError)));
        when(bookMaskValidator.validate(bookMask)).thenReturn(Set.of());

        Collection<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(expectedError, errors.stream().findFirst().get().getDescription());
    }

    //invalid mask
    @Test
    public void invalidMask() {
        request.setBookMask(bookMask);
        request.setIsbn(isbn);
        String expectedError = "Mask ERROR";
        when(isbnValidator.validate(isbn)).thenReturn(Set.of());
        when(bookMaskValidator.validate(bookMask)).thenReturn(Set.of(new ValidationError(expectedError)));

        Collection<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(expectedError, errors.stream().findFirst().get().getDescription());
    }
}
