package com.example.books_service.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
class JwtTokenServiceImpl implements JwtTokenService{

    @Override
    public String generateToken(String username) {
        // Используйте безопасный ключ
        // Срок действия токена (1 час)
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_00)) // 1 день
                .signWith(Keys.hmacShaKeyFor("secretsecretsecretsecretsecretsecret".getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}
