package com.example.books_service.core.utils;

import com.example.books_service.core.dto.User;
import com.example.books_service.core.dto.library.Author;
import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.dto.library.builder.AuthorBuilder;
import com.example.books_service.core.dto.library.builder.BookBuilder;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.domain.UserEntity;
import com.example.books_service.core.model.repos.AuthorRepository;
import com.example.books_service.core.model.repos.GenreRepository;
import com.example.books_service.core.model.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class EntityConverterImpl implements EntityConverter{
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository findGenreRepository;

    @Autowired
    private UserRepository userRepository;

    public User fromEntity(UserEntity entity) {
        return new User(entity.getUsername(), entity.getPassword());
    }

    public UserEntity toEntity(User user) {
        Optional<UserEntity> optional = userRepository.findByUsername(user.getUsername());
        if (optional.isPresent()) {
            return optional.get();
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        return entity;
    }
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
        if (author.getBirthDate() != null) {
            entity.setBirthDate(new java.sql.Date(author.getBirthDate().getTime()));
        }
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
