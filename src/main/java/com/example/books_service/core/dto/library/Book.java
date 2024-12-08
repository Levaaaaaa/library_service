package com.example.books_service.core.dto.library;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @NotEmpty(message = "ISBN must be not empty")
    @Pattern(regexp = "^[0-9]{13}$", message = "ISBN can have only 13 digits")
    private String isbn;

    @NotEmpty(message = "title must be not empty")
    @Size(max = 200, message = "Very long title of book. Max size - 200")
    private String title;

    @NotEmpty(message = "Book must have at least one genre")
    private List<@NotBlank String> genres;

    @Size(max = 1000, message = "Very long description of book, max size - 1000")
    private String description;

    @NotNull(message = "Book must have an author")
    private Author author;
}
