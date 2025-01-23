package com.example.books_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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


    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;


    @Column(name = "title", nullable = false)
    private String title;


    @ManyToMany
    @JoinTable(name = "genres_of_books", joinColumns = @JoinColumn(name = "book"), inverseJoinColumns = @JoinColumn(name = "genre"))
    private List<GenreEntity> genres;


    @Column(name = "description")
    private String description;

    @NotNull(message = "Book must have an author")
    //todo remove cascade type
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author", nullable = false)
    private AuthorEntity author;
}
