package com.example.books_service.core.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "First name must not be empty")
    @Size(max = 50, message = "First Name is too long, max - 50")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name must not be empty")
    @Size(max = 50, message = "Last Name is too long, max - 50")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 50, message = "Patronymic is too long, max - 50")
    @Column(name = "patronymic")
    private String patronymic;

    @Past(message = "Date of birth must be in the past")
    @Column(name = "birth_date")
    private Date birthDate;

    @Size(max = 100, message = "Author email is too long, max - 100")
    @Pattern(regexp = "^[A-Za-z0-9]+@[a-z]+.[a-z]+$", message = "Invalid author email")
    @Column(name = "email")
    private String email;
}
