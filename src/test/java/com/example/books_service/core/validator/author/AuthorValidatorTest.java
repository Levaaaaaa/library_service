package com.example.books_service.core.validator.author;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.builder.AuthorBuilder;
import com.example.books_service.core.validator.author.AuthorValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AuthorValidatorTest {
    @Autowired
    private AuthorValidator validator;

    private Author author;

    @Test
    public void correctAuthor() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withLastName("LastName")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("example@email.com")
                .build();
        assertTrue(validator.validate(author).isEmpty());
    }

    @Test
    public void firstNameIsNull() {
        author = AuthorBuilder.create()
                .withLastName("LastName")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("example@email.com")
                .build();
        assertEquals(1, validator.validate(author).size());
    }

    @Test
    public void firstNameIsEmpty() {
        author = AuthorBuilder.create()
                .withFirstName("")
                .withLastName("LastName")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("example@email.com")
                .build();
        assertEquals(1, validator.validate(author).size());
    }

    @Test
    public void lastNameIsNull() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("example@email.com")
                .build();
        assertEquals(1, validator.validate(author).size());
    }

    @Test
    public void lastNameIsEmpty() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withLastName("")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("example@email.com")
                .build();
        assertEquals(1, validator.validate(author).size());
    }

    @Test
    public void birthDateIsNull() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withLastName("LastName")
                .withEmail("example@email.com")
                .build();
        assertEquals(0, validator.validate(author).size());
    }

    @Test
    public void birthDateIsNotInThePast() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withLastName("")
                .withBirthDate(new java.util.Date())
                .withEmail("example@email.com")
                .build();
        assertEquals(1, validator.validate(author).size());
    }

    @Test
    public void emailIsNull() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withLastName("")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .build();
        assertEquals(1, validator.validate(author).size());
    }

    @Test
    public void emailIsEmpty() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withLastName("lastName")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("")
                .build();
        assertEquals(1, validator.validate(author).size());
    }

    @Test
    public void invalidEmailTest() {
        author = AuthorBuilder.create()
                .withFirstName("FirstName")
                .withLastName("LastName")
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("InvalidEmail")
                .build();
        assertEquals(1, validator.validate(author).size());
    }

    @Test
    public void firstNameAndLastNameIsNull() {
        author = AuthorBuilder.create()
                .withBirthDate(new java.util.Date(Date.valueOf("1990-02-02").getTime()))
                .withEmail("example@email.com")
                .build();
        assertEquals(2, validator.validate(author).size());
    }
}
