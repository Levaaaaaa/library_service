package com.example.books_service.core.model.repos;

import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    private String isbn;
    @Test
    public void notNull() {
        assertNotNull(repository);
    }

    @Test
    public void bookNotFound() {
        isbn = "0000000000000";
        assertTrue(repository.findByIsbn(isbn).isEmpty());
    }

    @Test
    public void boonIsFound() {
        isbn = "1111111111111";
        Optional<BookEntity> optional = repository.findByIsbn(isbn);
        assertTrue(optional.isPresent());

        BookEntity book = optional.get();
        assertEquals("Test title 1", book.getTitle());
        assertEquals(isbn, book.getIsbn());
        AuthorEntity author = book.getAuthor();
        assertEquals("John", author.getFirstName());
        assertEquals("Test author 1", author.getLastName());
    }

    @Test
    public void findByIdTest() {
        Optional<BookEntity> optional = repository.findById(1L);
        assertTrue(optional.isPresent());
        BookEntity book = optional.get();
        assertEquals("Test title 1", book.getTitle());
        assertEquals("1111111111111", book.getIsbn());
        assertNotNull(book.getGenres());
        assertFalse(book.getGenres().isEmpty());
        assertEquals("fantasy", book.getGenres().getFirst().getGenre());
        assertNotNull(book.getAuthor());
        assertEquals("John", book.getAuthor().getFirstName());
        assertEquals("Test author 1", book.getAuthor().getLastName());
    }
}
