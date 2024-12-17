package com.example.books_service.core.validator;

import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ValidationService {
    public boolean isValidBook(BookEntity book) throws ValidationException;
    public boolean isValidAuthor(AuthorEntity author) throws ValidationException;
    public boolean isValidGenre(GenreEntity genre) throws ValidationException;
    public boolean isValidBooksList(List<BookEntity> books) throws ValidationException;
}
