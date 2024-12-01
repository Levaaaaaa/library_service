package com.example.books_service.core.validator;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.builder.AuthorBuilder;
import com.example.books_service.core.dto.builder.BookBuilder;
import com.example.books_service.core.dto.request.AddBookRequest;
import com.example.books_service.core.validator.author.AuthorValidator;
import com.example.books_service.core.validator.book.BookValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GeneralValidatorTest {
    @InjectMocks
    private BookAndAuthorValidatorImpl validator;

    @Mock
    private BookValidator bookValidator;

    @Mock
    private AuthorValidator authorValidator;

    private AddBookRequest request;
    private Book book;
    private Author author;

    @Test
    public void validatorIsNotNull() {
        assertNotNull(validator);
    }

    @Test
    public void correctRequest() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withLastName("LastName")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("example@email.com")
                .build();
        book = BookBuilder.create()
                .withISBN("1234567891011")
                .withTitle("Title")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(author)
                .build();
        request = new AddBookRequest(book);

        when(bookValidator.validate(book)).thenReturn(Set.of());
        when(authorValidator.validate(author)).thenReturn(Set.of());

        Set<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void invalidBook() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withLastName("LastName")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("example@email.com")
                .build();
        book = BookBuilder.create()
                .withISBN("")
                .withTitle("Title")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(author)
                .build();
        request = new AddBookRequest(book);

        when(bookValidator.validate(book)).thenReturn(Set.of(new ValidationError("Empty ISBN")));
        when(authorValidator.validate(author)).thenReturn(Set.of());

        Set<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
    }

    @Test
    public void invalidAuthor() {
        author = AuthorBuilder.create()
                .withFirstName("")
                .withLastName("LastName")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("example@email.com")
                .build();
        book = BookBuilder.create()
                .withISBN("1112223334445")
                .withTitle("Title")
                .withGenre(List.of("genre"))
                .withDescription("description")
                .withAuthor(author)
                .build();
        request = new AddBookRequest(book);

        when(bookValidator.validate(book)).thenReturn(Set.of());
        when(authorValidator.validate(author)).thenReturn(Set.of(new ValidationError("Empty first Name")));

        Set<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
    }
}
