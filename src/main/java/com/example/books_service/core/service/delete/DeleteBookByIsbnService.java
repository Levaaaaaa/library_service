package com.example.books_service.core.service.delete;

import com.example.books_service.core.dto.request.IsbnRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface DeleteBookByIsbnService {
    public CommonResponse deleteBook(IsbnRequest request);
}
