package com.example.books_service.validator.author;

import com.example.books_service.core.dto.Author;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class AuthorValidatorTest {
    @Autowired
    private AuthorValidator validator;

    private Author author;
}
