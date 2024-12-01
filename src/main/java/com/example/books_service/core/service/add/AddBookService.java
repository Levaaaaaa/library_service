package com.example.books_service.core.service.add;

import com.example.books_service.core.dto.request.AddBookRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface AddBookService {
    public CommonResponse addBook(AddBookRequest request);
}
