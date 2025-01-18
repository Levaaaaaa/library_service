package com.example.books_service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.example.books_service.ReadJsonUtil.readJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;


public abstract class AbstractRestControllerTest {
//    @LocalServerPort
//    private static String port;

    private static final String base = "http://localhost:8080";
    private static final String testCaseBase = "test_cases/";

    @Autowired
    private MockMvc mockMvc;

    protected void getRequestExecuteAndCompare() throws Exception{
        String actualResponse = executeGet(base + getUrl());
        String expectedJsonResponse = readJson(testCaseBase + getFolderName() + "/response.json");
        compare(actualResponse, expectedJsonResponse);
    }

    protected void postRequestExecuteAndCompare() throws Exception {
        String testCasePath = testCaseBase + getFolderName();
        String request = readJson(testCasePath + "/request.json");
        String actualResponse = executePost(base + getUrl(), request);
        String expectedResponse = readJson(testCasePath + "/response.json");
        compare(expectedResponse, actualResponse);
    }

    private String executeGet(String url) throws Exception{
        return execute(get(url));
    }

    private String executePost(String url, String body) throws Exception {
        return execute(post(url, body));
    }
    private String execute(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(
                        builder)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private void compare(String expectedResponse, String actualResponse) {
        assertJson(actualResponse).where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponse);
    }

    protected abstract String getUrl();
    protected abstract String getFolderName();
    public abstract void execute() throws Exception;
}
