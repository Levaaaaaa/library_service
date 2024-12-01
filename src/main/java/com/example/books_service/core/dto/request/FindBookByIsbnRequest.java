package com.example.books_service.core.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindBookByIsbnRequest {
    @NotEmpty(message = "ISBN must not be empty")
    @Pattern(regexp = "^[0-9]{13}$", message = "ISBN can have only 13 digits")
    private String isbn;
}
