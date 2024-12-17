package com.example.books_service.core.service.library;

import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.repos.BookRepository;
import com.example.books_service.core.utils.ValidationException;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CrudServiceImpl implements CrudService{
    @Autowired
    private ValidationService validationService;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void add(List<BookEntity> books) throws ValidationException {
        if (validationService.isValidBooksList(books)) {
            bookRepository.saveAll(
                    books.stream()
                            .filter(book ->
                            {
                                return bookRepository.findByIsbn(book.getIsbn()).isEmpty();
                            })
                            .toList()
            );
        }
    }

    @Override
    public BookEntity findById(Long id) throws ValidationException {
        Optional<BookEntity> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        }
        throw new ValidationException(List.of(new ValidationError("Book with id " + id + " not found")));
    }

    @Override
    public BookEntity findByIsbn(String isbn) throws ValidationException {
        Optional<BookEntity> bookOptional = bookRepository.findByIsbn(isbn);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        }
        throw new ValidationException(List.of(new ValidationError("Book with isbn " + isbn + " not found")));
    }

    @Override
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void updateById(Long id, BookEntity book) throws ValidationException {
        if (validationService.isValidBook(book)) {
            Optional<BookEntity> optionalBook = bookRepository.findById(id);
            if (optionalBook.isEmpty()) {
                throw new ValidationException(List.of(new ValidationError("Book with id " + id + " not found. You can add it.")));
            }

            BookEntity foundBook = optionalBook.get();
            foundBook = updateBookFields(foundBook, book);
            bookRepository.save(foundBook);
        }
    }

    @Override
    public void deleteById(Long id) throws ValidationException {
        bookRepository.deleteById(id);
    }

    private BookEntity updateBookFields(BookEntity oldBook, BookEntity newBook) {
        oldBook.setIsbn(newBook.getIsbn());
        oldBook.setTitle(newBook.getTitle());
        oldBook.setDescription(newBook.getDescription());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setGenres(newBook.getGenres());
        return oldBook;
    }
}
