package com.example.books_service.core.model.repos;

import com.example.books_service.core.model.domain.AuthorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    public Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
