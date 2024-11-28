package com.example.books_service.validator.book;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.builder.BookBuilder;
import com.example.books_service.validator.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookValidatorTest {
    @Autowired
    private BookValidator validator;

    private Book book;

    @Test
    public void validatorIsNotNull() {
        assertNotNull(validator);
    }

    @Test
    public void IsbnIsNull() {
        book = BookBuilder.create()
                .withTitle("Title")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }

    @Test
    public void isbnIsEmpty() {
        book = BookBuilder.create()
                .withISBN("")
                .withTitle("Title")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(2, errors.size());
    }

    @Test
    public void IsbnIsTooSmall() {
        book = BookBuilder.create()
                .withISBN("59999999")
                .withTitle("Title")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }

    @Test
    public void IsbnIsNoDigits() {
        book = BookBuilder.create()
                .withISBN("abacababcdefg")
                .withTitle("Title")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }

    @Test
    public void IsbnTooLong() {
        book = BookBuilder.create()
                .withISBN("123456789101112")
                .withTitle("Title")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }

    @Test
    public void titleIsNull() {
        book = BookBuilder.create()
                .withISBN("1234567891012")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }
    @Test
    public void titleIsEmpty() {
        book = BookBuilder.create()
                .withISBN("1234567891012")
                .withTitle("")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }

    @Test
    public void GenreIsNull() {
        book = BookBuilder.create()
                .withISBN("1234567891012")
                .withTitle("Some title")
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }

    @Test
    public void GenreIsEmpty() {
        book = BookBuilder.create()
                .withISBN("1234567891012")
                .withTitle("Some title")
                .withGenre(List.of())
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }

    @Test
    public void GenreIsBlank() {
        book = BookBuilder.create()
                .withISBN("1234567891012")
                .withTitle("Some title")
                .withGenre(List.of(""))
                .withDescription("description")
                .withAuthor(new Author())
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }

    @Test
    public void authorIsNull() {
        book = BookBuilder.create()
                .withISBN("1234567891012")
                .withTitle("Some title")
                .withGenre(List.of("genre"))
                .withDescription("Some description")
                .build();
        Set<ValidationError<Book>> errors = validator.validate(book);
        assertEquals(1, errors.size());
    }
}
