package com.example.books_service.core.service.library.add;

import com.example.books_service.core.dto.library.request.AddBookRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface AddBookService {
    public CommonResponse addBook(AddBookRequest request);
}
