package com.example.books_service.rest;

import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.service.library.CrudService;

import com.example.books_service.core.utils.ValidationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/")
public class CrudController {
    @Autowired
    private CrudService crudService;

    @GetMapping("/find")
    public List<BookEntity> findAll() {
        return crudService.findAll();
    }

    @GetMapping("find/id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(crudService.findById(id));
        }
        catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getViolations());
        }
    }

    @GetMapping("find/isbn/{isbn}")
    public ResponseEntity<?> findByIsbn(@PathVariable("isbn") String isbn) {
        try {
            return ResponseEntity.ok(crudService.findByIsbn(isbn));
        }
        catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getViolations());
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> addBooksList(@RequestBody List<BookEntity> books) {
        if (books == null || books.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty request!");
        }
        try {
            crudService.add(books);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getViolations());
        }
        return ResponseEntity.ok("All books was added successfully!");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody BookEntity book) {
        try {
            crudService.updateById(id, book);
        }
        catch (ValidationException e) {
            ResponseEntity.badRequest().body(e.getViolations());
        }
        return ResponseEntity.ok("Book with id " + id + "is updated successfully!");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            crudService.deleteById(id);
        }
        catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getViolations());
        }
        return ResponseEntity.ok("Operation successful!");
    }
}
