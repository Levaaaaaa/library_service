package com.example.books_service.core.model.repos;

import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.repos.FindBookByIdRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FindBookByIdRepositoryTest {
    @Autowired
    private FindBookByIdRepository repository;

    @Test
    public void notNull() {
        assertNotNull(repository);
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
