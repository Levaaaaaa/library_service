package com.example.books_service.core.dto.builder;

import com.example.books_service.core.dto.Author;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class AuthorBuilder {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthDate;
    private String email;

    public static AuthorBuilder create() {
        return new AuthorBuilder();
    }

    public Author build() {
        return new Author(
                this.firstName,
                this.lastName,
                this.patronymic,
                this.birthDate,
                this.email);
    }

    public AuthorBuilder withFirstName(String name) {
        this.firstName = name;
        return this;
    }

    public AuthorBuilder withLastName(String name) {
        this.lastName = name;
        return this;
    }

    public AuthorBuilder withPatronymic(String name) {
        this.patronymic = name;
        return this;
    }

    public AuthorBuilder withBirthDate(Date val) {
        this.birthDate = val;
        return this;
    }

    public AuthorBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
}
