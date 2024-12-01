package com.example.books_service.core.service.find;

import com.example.books_service.core.dto.request.FindBookByIsbnRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface FindBookByIsbnService {
    public CommonResponse findByIsbn(FindBookByIsbnRequest request);
}
