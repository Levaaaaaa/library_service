package com.example.books_service.core.dto.library.request;

import com.example.books_service.core.dto.library.BookMask;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {
    @NotNull(message = "Updated book must not be null")
    private BookMask bookMask;
    @NotNull(message = "ISBN must not be null")
    private IsbnRequest isbn;
}
