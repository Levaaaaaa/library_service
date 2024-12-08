package com.example.books_service.core.service.library.delete;

import com.example.books_service.core.dto.library.request.IsbnRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface DeleteBookByIsbnService {
    public CommonResponse deleteBook(IsbnRequest request);
}
