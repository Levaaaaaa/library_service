package com.example.books_service.integration.update;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateNotExistingBookTest extends AbstractTestUpdate {
    @Override
    protected String getUrl() {
        return super.getUrl() + "/50";
    }

    @Override
    protected String getFolderName() {
        return super.getFolderName() + "/not_existing_book";
    }

    @Override
    @Test
    @WithMockUser
    public void execute() throws Exception {
        executeAndComparePut(HttpStatus.NOT_FOUND);
    }
}
