package com.example.books_service.core.validator.update;

import com.example.books_service.core.dto.library.Author;
import com.example.books_service.core.dto.library.BookMask;
import com.example.books_service.core.dto.library.builder.BookMaskBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookMaskValidatorTest {
    @Autowired
    private BookMaskValidator validator;

    private BookMask book;

    @Test
    public void notNull() {
        assertNotNull(validator);
    }

    @Test
    public void correctBook() {
        book = BookMaskBuilder.create()
                .withTitle("Title 1")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        assertTrue(validator.validate(book).isEmpty());
    }

    @Test
    public void titleIsNull() {
        book = BookMaskBuilder.create()
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        assertEquals(0, validator.validate(book).size());
    }
    @Test
    public void titleIsEmpty() {
        book = BookMaskBuilder.create()
                .withTitle("")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        assertEquals(1, validator.validate(book).size());
    }

    @Test
    public void titleIsNullAndIsbnIsNull() {
        book = BookMaskBuilder.create()
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        assertEquals(0, validator.validate(book).size());
    }
    @Test
    public void GenreIsNull() {
        book = BookMaskBuilder.create()
                .withTitle("Some title")
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        assertEquals(0, validator.validate(book).size());
    }

    @Test
    public void GenreIsEmpty() {
        book = BookMaskBuilder.create()
                .withTitle("Some title")
                .withGenre(List.of())
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        assertEquals(0, validator.validate(book).size());
    }

    @Test
    public void GenreIsBlank() {
        book = BookMaskBuilder.create()
                .withTitle("Some title")
                .withGenre(List.of(""))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        assertEquals(1, validator.validate(book).size());
    }

    @Test
    public void authorIsNull() {
        book = BookMaskBuilder.create()
                .withTitle("Some title")
                .withGenre(List.of("genre"))
                .withDescription("Some description")
                .build();
        assertEquals(0, validator.validate(book).size());
    }
}
