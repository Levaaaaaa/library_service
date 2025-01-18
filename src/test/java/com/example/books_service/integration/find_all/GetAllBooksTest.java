package com.example.books_service.integration.find_all;

import com.example.books_service.TestSecurityConfig;
import com.example.books_service.integration.AbstractRestControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
public class GetAllBooksTest extends AbstractRestControllerTest {
    @Override
    protected String getUrl() {
        return "/find";
    }

    @Override
    protected String getFolderName() {
        return "find_all";
    }

    @Override
    @Test
    @WithMockUser(roles = "USER")
    public void execute() throws Exception {
        getRequestExecuteAndCompare();
    }
}
