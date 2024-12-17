package com.example.books_service.core.service.library;

import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudService {
    public void add(List<BookEntity> books) throws ValidationException;
    public BookEntity findById(Long id) throws ValidationException;
    public BookEntity findByIsbn(String isbn) throws ValidationException;
    public List<BookEntity> findAll();
    public void updateById(Long id, BookEntity book) throws ValidationException;
    public void deleteById(Long id) throws ValidationException;
}

