package com.example.books_service.service.library;

import com.example.books_service.dto.BookDTO;
import com.example.books_service.entities.AuthorEntity;
import com.example.books_service.entities.BookEntity;
import com.example.books_service.entities.GenreEntity;
import com.example.books_service.repos.AuthorRepository;
import com.example.books_service.repos.BookRepository;
import com.example.books_service.repos.GenreRepository;
import com.example.books_service.utils.BookMapper;
import com.example.books_service.utils.ValidationException;
import com.example.books_service.validator.ValidationError;
import com.example.books_service.validator.ValidationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrudServiceImpl implements CrudService{
    @Autowired
    ValidationService validationService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookMapper bookMapper;

    @Override
    public void add(List<BookDTO> books) {
        //if (validationService.isValidBooksList(books)) {
            List<BookEntity> bookEntiites = books.stream()
                    .map(bookMapper::toEntity)
                    .filter(book ->
                    {
                        return bookRepository.findByIsbn(book.getIsbn()).isEmpty();
                    })
                    .map(this::updateGenres)
                    .toList();
            bookRepository.saveAll(
                    bookEntiites
            );
        //}
    }

    @Override
    public BookDTO findById(Long id) throws EntityNotFoundException {
        Optional<BookEntity> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return bookMapper.toDTO(bookOptional.get());
        }
        throw new EntityNotFoundException("Book with id " + id + " not found");
    }

    @Override
    public BookDTO findByIsbn(String isbn) throws EntityNotFoundException {
        Optional<BookEntity> bookOptional = bookRepository.findByIsbn(isbn);
        if (bookOptional.isPresent()) {
            return bookMapper.toDTO(bookOptional.get());
        }
        throw new EntityNotFoundException("Book with isbn " + isbn + " not found");
    }

    @Override
    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDTO).toList();
    }

    @Override
    public void updateById(Long id, BookDTO book) throws EntityNotFoundException {
        //if (validationService.isValidBook(book)) {
            Optional<BookEntity> optionalBook = bookRepository.findById(id);
            if (optionalBook.isEmpty()) {
                throw new EntityNotFoundException("Book with id " + id + " not found. You can add it.");
            }

            BookEntity foundBook = optionalBook.get();
            foundBook = updateBookFields(foundBook, bookMapper.toEntity(book));
            bookRepository.save(foundBook);
        //}
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    //or can add all genres into one transaction
    private BookEntity updateBookFields(BookEntity oldBook, BookEntity newBook) {
        oldBook.setIsbn(newBook.getIsbn());
        oldBook.setTitle(newBook.getTitle());
        oldBook.setDescription(newBook.getDescription());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setGenres(newBook.getGenres());
        return oldBook;
    }

    private BookEntity updateGenres(BookEntity bookEntity) {
        bookEntity.setGenres(
                bookEntity.getGenres().stream()
                        .map(genre -> {
                            Optional<GenreEntity> optionalGenre = genreRepository.findByGenre(genre.getGenre());
                            if (optionalGenre.isEmpty()) {
                                genreRepository.save(genre);
                                return genre;
                            }
                            else {
                                return optionalGenre.get();
                            }
                        })
                        .toList()
        );
        return  bookEntity;
    }

    //todo update authors
}
