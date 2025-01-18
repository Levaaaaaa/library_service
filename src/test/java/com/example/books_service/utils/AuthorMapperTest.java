package com.example.books_service.utils;

import com.example.books_service.dto.AuthorDTO;
import com.example.books_service.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Import(AuthorMapperImpl.class)
public class AuthorMapperTest {
    @Autowired
    private AuthorMapper authorMapper;

    private AuthorDTO dto;
    private AuthorEntity entity;

    @Test
    public void notNullTest() {
        assertNotNull(authorMapper);
    }

    @Test
    public void toDtoCorrectMappingTest() {
        Long id = 1L;
        String fname = "fname", lname = "lname", patr = "patr", email = "email";
        Date birthDate = Date.valueOf("2000-02-02");
        entity = AuthorEntity.builder().id(id)
                .firstName(fname)
                .lastName(lname)
                .patronymic(patr)
                .email(email)
                .birthDate(birthDate)
                .build();
        dto = authorMapper.toDTO(entity);
        assertNotNull(dto);
        assertEquals(fname, dto.firstName());
        assertEquals(lname, dto.lastName());
        assertEquals(patr, dto.patronymic());
        assertEquals(email, dto.email());
        assertEquals(birthDate, dto.birthDate());
    }
}
