package com.example.books_service.integration.add;

import com.example.books_service.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
public class AddBookOkTest extends AbstractTestAddBook {
    @Override
    protected String getFolderName() {
        return super.getFolderName() + "/add_correct_book";
    }

    @Override
    @Test
    @WithMockUser(roles = "USER")
    public void execute() throws Exception {
        executeAndComparePost(HttpStatus.OK);
    }
}
