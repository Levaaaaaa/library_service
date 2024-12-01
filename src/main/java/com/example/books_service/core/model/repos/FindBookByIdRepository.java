package com.example.books_service.core.model.repos;

import com.example.books_service.core.model.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FindBookByIdRepository extends JpaRepository<BookEntity, Long> {
    public Optional<BookEntity> findById(Long id);
}
