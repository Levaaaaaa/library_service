package com.example.books_service.integration.add;

import com.example.books_service.integration.AbstractRestControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class AddBookTest extends AbstractRestControllerTest {
    @Override
    protected String getUrl() {
        return "/add";
    }

    @Override
    protected String getFolderName() {
        return "add/add_correct_book";
    }

    @Override
    @Test
    @WithMockUser(roles = "USER")
    public void execute() throws Exception {
        postRequestExecuteAndCompare();
    }
}
