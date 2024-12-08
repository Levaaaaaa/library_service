package com.example.books_service.core.validator.find;

import com.example.books_service.core.dto.library.request.FindBookByIdRequest;
import com.example.books_service.core.validator.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IdValidatorTest {
    @Autowired
    private IdValidator validator;

    private FindBookByIdRequest request;

    private String descr1 = "Request must not be null";
    private String descr2 = "Id must not be null!";

    @Test
    public void notNull() {
        assertNotNull(validator);
    }

    @Test
    public void requestIsNullTest() {
        Set<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(descr1, errors.stream().findFirst().get().getDescription());
    }

    @Test
    public void idIsNullTest() {
        request = new FindBookByIdRequest();
        Set<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(descr2, errors.stream().findFirst().get().getDescription());
    }

    @Test
    public void idIsCorrectTest() {
        request = new FindBookByIdRequest(1L);
        assertTrue(validator.validate(request).isEmpty());
    }
}
