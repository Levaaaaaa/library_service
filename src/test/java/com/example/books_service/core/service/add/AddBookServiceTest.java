package com.example.books_service.core.service.add;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.request.AddBookRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.validator.BookAndAuthorValidator;
import com.example.books_service.core.validator.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddBookServiceTest {
    @InjectMocks
    private AddBookServiceImpl service;

    @Mock
    private BookAndAuthorValidator validator;

    private AddBookRequest request;
    private CommonResponse expectedResponse;

    @Test
    public void serviceNotNull() {
        assertNotNull(service);
    }
    @Test
    public void correctRequest() {
        request = new AddBookRequest(new Book());

        when(validator.validate(request)).thenReturn(Set.of());
        CommonResponse response = service.addBook(request);

        assertNotNull(response);
        assertNotEquals(null, response.getBook());
        assertEquals(0, response.getErrors().size());
    }
    @Test
    public void incorrectRequest() {
        request = new AddBookRequest(new Book());

        when(validator.validate(request)).thenReturn(Set.of(new ValidationError("test error")));
        CommonResponse response = service.addBook(request);

        assertNotNull(response);
        assertEquals(null, response.getBook());
        assertEquals(1, response.getErrors().size());
    }
}
