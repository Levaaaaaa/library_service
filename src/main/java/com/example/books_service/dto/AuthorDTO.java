package com.example.books_service.dto;

import jakarta.validation.constraints.*;

import java.sql.Date;

public record AuthorDTO(
        @NotEmpty(message = "First name must not be empty")
        @Size(max = 50, message = "First Name is too long, max - 50")
        String firstName,

        @NotEmpty(message = "Last name must not be empty")
        @Size(max = 50, message = "Last Name is too long, max - 50")
        String lastName,

        @Size(max = 50, message = "Patronymic is too long, max - 50")
        String patronymic,

        @Past(message = "Date of birth must be in the past")
        Date birthDate,

        @Size(max = 100, message = "Author email is too long, max - 100")
        @Pattern(regexp = "^[A-Za-z0-9]+@[a-z]+.[a-z]+$", message = "Invalid author email")
        String email
) {
}
