package com.example.books_service.validator;

import com.example.books_service.dto.Response;
import com.example.books_service.utils.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.amazonaws.services.shield.model.ResourceAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Response handleNotFoundException(EntityNotFoundException e, WebRequest request) {
        return new Response(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationException(MethodArgumentNotValidException e, WebRequest request) {
        StringBuilder errors = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.append(error.getField()).append(" = `").append(error.getRejectedValue()).append("` but ").append(error.getDefaultMessage()).append(";");
        });
        return new Response(errors.toString());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({PSQLException.class, ResourceAlreadyExistsException.class})
    public Response handlePSQLException(Exception e, WebRequest request) {
        return new Response(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Response handleConstrainViolationException(ConstraintViolationException e, WebRequest request) {
        return new Response(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public Response handleAuthenticationException(AuthenticationException e, WebRequest request) {
        return new Response(e.getMessage());
    }
}
