package com.example.books_service.integration.delete;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteBookOkTest extends AbstractTestDeleteBookById {
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
    @WithMockUser(roles = "USER")
    public void execute() throws Exception {
        executeAndCompareDelete(HttpStatus.OK);
    }
}
