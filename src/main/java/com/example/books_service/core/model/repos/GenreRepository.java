package com.example.books_service.core.model.repos;

import com.example.books_service.core.model.domain.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    public Optional<GenreEntity> findByGenre(String genre);
}
