package com.example.books_service.integration.find_by_id;

import com.example.books_service.TestSecurityConfig;
import com.example.books_service.integration.AbstractTestRestController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
public class GetBookWithId3Test extends AbstractTestRestController {

    @Override
    protected String getUrl() {
        return "/find/id/3";
    }

    @Override
    protected String getFolderName() {
        return "find_by_id/id_3";
    }

    @Override
    @Test
    @WithMockUser(roles = "USER")
    public void execute() throws Exception {
        executeAndCompareGet(HttpStatus.OK);
    }
}
