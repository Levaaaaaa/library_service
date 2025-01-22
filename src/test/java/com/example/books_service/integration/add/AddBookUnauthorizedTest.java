package com.example.books_service.integration.add;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@AutoConfigureMockMvc
public class AddBookUnauthorizedTest extends AbstractTestAddBook {
    @Override
    protected String getFolderName() {
        return super.getFolderName() + "/unauthorized";
    }

    @Override
    @Test
    public void execute() throws Exception {
        executeAndComparePost(HttpStatus.UNAUTHORIZED);
    }
}
