package com.example.books_service.core.dto.library.builder;

import com.example.books_service.core.dto.library.Author;

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
