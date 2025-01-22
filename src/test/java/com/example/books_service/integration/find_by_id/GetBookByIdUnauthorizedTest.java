package com.example.books_service.integration.find_by_id;

import com.example.books_service.integration.AbstractTestRestController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@AutoConfigureMockMvc
public class GetBookByIdUnauthorizedTest extends AbstractTestRestController {
    @Override
    protected String getUrl() {
        return "/find/id/1";
    }

    @Override
    protected String getFolderName() {
        return "find_by_id/unauthorized";
    }

    @Override
    @Test
    public void execute() throws Exception {
        executeAndCompareGet(HttpStatus.UNAUTHORIZED);
    }
}
