package com.example.books_service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
public class TestSecurityConfig {
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("test_user").password("test_user_password").roles("USER");
    }
}
