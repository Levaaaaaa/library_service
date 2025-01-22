package com.example.books_service.integration.update;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateBookOkTest extends AbstractTestUpdate {
    @Override
    protected String getUrl() {
        return super.getUrl() + "/1";
    }

    @Override
    protected String getFolderName() {
        return super.getFolderName() + "/ok";
    }

    @Override
    @Test
    @WithMockUser(roles = "user")
    public void execute() throws Exception {
        executeAndComparePut(HttpStatus.OK);
    }
}
