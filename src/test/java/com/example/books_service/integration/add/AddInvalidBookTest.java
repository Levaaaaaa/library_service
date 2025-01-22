package com.example.books_service.integration.add;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class AddInvalidBookTest extends AbstractTestAddBook {
    @Override
    protected String getFolderName() {
        return super.getFolderName() + "/invalid_book";
    }

    @Override
    @Test
    @WithMockUser(roles = "user")
    public void execute() throws Exception {
        executeAndComparePost(HttpStatus.BAD_REQUEST);
    }
}
