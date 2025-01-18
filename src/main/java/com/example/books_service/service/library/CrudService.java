package com.example.books_service.service.library;

import com.example.books_service.dto.BookDTO;
import com.example.books_service.utils.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudService {
    public void add(List<BookDTO> books);
    public BookDTO findById(Long id) throws EntityNotFoundException;
    public BookDTO findByIsbn(String isbn) throws EntityNotFoundException;
    public List<BookDTO> findAll();
    public void updateById(Long id, BookDTO book) throws EntityNotFoundException;
    public void deleteById(Long id);
}

