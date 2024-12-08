package com.example.books_service.core.dto.library;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMask{
    @Size(max = 200, message = "Very long title of book. Max size - 200")
    @Pattern(regexp = "^[0-9a-zA-Z ]+$", message = "Title must not be blank")
    private String title;

    private List<@NotBlank(message = "Genre must not be blank") String> genres;

    @Size(max = 1000, message = "Very long description of book, max size - 1000")
    private String description;

    private Author author;
}
