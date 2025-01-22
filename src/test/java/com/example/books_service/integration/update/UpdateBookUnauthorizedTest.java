package com.example.books_service.integration.update;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateBookUnauthorizedTest extends AbstractTestUpdate {
    @Override
    protected String getUrl() {
        return super.getUrl() + "/1";
    }

    @Override
    protected String getFolderName() {
        return super.getFolderName() + "/unauthorized";
    }

    @Override
    @Test
    public void execute() throws Exception {
        executeAndCompareDelete(HttpStatus.UNAUTHORIZED);
    }
}
