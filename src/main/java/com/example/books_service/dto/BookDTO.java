package com.example.books_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record BookDTO (

        @Positive(message = "Book id must be positive")
        Long id,

    @NotEmpty(message = "ISBN must be not empty")
    @Pattern(regexp = "^[0-9]{13}$", message = "ISBN can have only 13 digits")
    String isbn,

    @NotEmpty(message = "title must be not empty")
    @Size(max = 200, message = "Very long title of book. Max size - 200")
    String title,

    @NotEmpty(message = "Book must have at least one genre")
    List<@NotNull(message = "Genre must not be null!") @Valid GenreDTO> genres,

    @Size(max = 1000, message = "Very long description of book, max size - 1000")
    String description,

    @Valid
    AuthorDTO author
)
{}
