package com.example.books_service.core.service.library.add;

import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.dto.library.request.AddBookRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import com.example.books_service.core.dto.library.response.ManyBooksResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddBookService {
    public CommonResponse addBook(AddBookRequest request);
    public ManyBooksResponse addAll(List<Book> bookList);
}
