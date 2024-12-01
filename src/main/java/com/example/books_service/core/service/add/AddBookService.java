package com.example.books_service.core.service.add;

import com.example.books_service.core.request.AddBookRequest;
import com.example.books_service.core.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface AddBookService {
    public CommonResponse addBook(AddBookRequest request);
}
