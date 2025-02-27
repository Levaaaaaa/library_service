package com.example.books_service.integration.find_all;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class GetAllBooksTest extends AbstractTestFindAll {

    @Override
    protected String getFolderName() {
        return super.getFolderName() + "/ok";
    }

    @Override
    @Test
    @WithMockUser(roles = "USER")
    public void execute() throws Exception {
        executeAndCompareGet(HttpStatus.OK);
    }
}
