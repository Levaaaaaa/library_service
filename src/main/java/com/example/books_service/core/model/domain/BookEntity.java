package com.example.books_service.core.model.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "ISBN must be not empty")
    @Pattern(regexp = "^[0-9]{13}$", message = "ISBN can have only 13 digits")
    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @NotEmpty(message = "title must be not empty")
    @Size(max = 200, message = "Very long title of book. Max size - 200")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "Book must have at least one genre")
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "genres_of_books", joinColumns = @JoinColumn(name = "book"), inverseJoinColumns = @JoinColumn(name = "genre"))
    private List<
            @NotNull(message = "Genre must not be null!")
            @Valid GenreEntity>
            genres;

    @Size(max = 1000, message = "Very long description of book, max size - 1000")
    @Column(name = "description")
    private String description;

    @Valid
    @NotNull(message = "Book must have an author")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author", nullable = false)
    private AuthorEntity author;
}
