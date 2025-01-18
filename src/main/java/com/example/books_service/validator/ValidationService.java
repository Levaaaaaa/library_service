package com.example.books_service.validator;

import com.example.books_service.entities.AuthorEntity;
import com.example.books_service.entities.BookEntity;
import com.example.books_service.entities.GenreEntity;
import com.example.books_service.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ValidationService {
    public boolean isValidBook(BookEntity book) throws ValidationException;
    public boolean isValidAuthor(AuthorEntity author) throws ValidationException;
    public boolean isValidGenre(GenreEntity genre) throws ValidationException;
    public boolean isValidBooksList(List<BookEntity> books) throws ValidationException;
}
