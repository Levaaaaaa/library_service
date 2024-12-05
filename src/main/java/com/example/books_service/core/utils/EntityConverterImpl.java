package com.example.books_service.core.utils;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.builder.AuthorBuilder;
import com.example.books_service.core.dto.builder.BookBuilder;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.repos.AuthorRepository;
import com.example.books_service.core.model.repos.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class EntityConverterImpl implements EntityConverter{
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository findGenreRepository;

    public Author fromEntity(AuthorEntity entity) {
        return AuthorBuilder.create()
                .withFirstName(entity.getFirstName())
                .withLastName(entity.getLastName())
                .withPatronymic(entity.getPatronymic())
                .withEmail(entity.getEmail())
                .withBirthDate(entity.getBirthDate())
                .build();
    }

    public Book fromEntity(BookEntity entity) {
        return BookBuilder.create()
                .withISBN(entity.getIsbn())
                .withTitle(entity.getTitle())
                .withDescription(entity.getDescription())
                .withGenre(entity.getGenres().stream().map(this::fromEntity).toList())
                .withAuthor(fromEntity(entity.getAuthor()))
                .build();
    }

    public String fromEntity(GenreEntity genre) {
        return genre.getGenre();
    }

    public BookEntity toEntity(Book book) {
        BookEntity result = new BookEntity();
        result.setIsbn(book.getIsbn());
        result.setTitle(book.getTitle());
        result.setDescription(book.getDescription());
        result.setGenres(book.getGenres().stream().map(this::toEntity).toList());
        result.setAuthor(toEntity(book.getAuthor()));
        return result;
    }

    public AuthorEntity toEntity(Author author) {
        Optional<AuthorEntity> optional = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        if (optional.isPresent()) {
            return optional.get();
        }
        AuthorEntity entity = new AuthorEntity();
        entity.setFirstName(author.getFirstName());
        entity.setLastName(author.getLastName());
        entity.setPatronymic(author.getPatronymic());
        entity.setEmail(author.getEmail());
        entity.setBirthDate(new java.sql.Date(author.getBirthDate().getTime()));
        return entity;
    }

    public GenreEntity toEntity(String genre) {
        Optional<GenreEntity> optional = findGenreRepository.findByGenre(genre);
        if (optional.isPresent()) {
            return optional.get();
        }
        GenreEntity entity = new GenreEntity();
        entity.setGenre(genre);
        return entity;
    }
}
