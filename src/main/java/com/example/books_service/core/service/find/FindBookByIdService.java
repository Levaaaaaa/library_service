package com.example.books_service.core.service.find;

import com.example.books_service.core.dto.request.FindBookByIdRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface FindBookByIdService {
    public CommonResponse findBookById(FindBookByIdRequest request);
}
