package com.example.books_service.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.example.books_service.ReadJsonUtil.readJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;


public abstract class AbstractTestRestController {
//    @LocalServerPort
//    private static String port;

    private static final String base = "http://localhost:8080";
    private static final String testCaseBase = "test_cases/";

    @Autowired
    private MockMvc mockMvc;

    protected void executeAndCompareGet(HttpStatus expectedStatus) throws Exception{
        ResultActions actualResponseActions = executeGet(base + getUrl());
        String expectedJsonResponse = readJson(testCaseBase + getFolderName() + "/response.json");
        compare(actualResponseActions, expectedStatus, expectedJsonResponse);
    }

    protected void executeAndComparePost(HttpStatus expectedStatus) throws Exception {
        String testCasePath = testCaseBase + getFolderName();
        String request = readJson(testCasePath + "/request.json");
        ResultActions actualResponseActions = executePost(base + getUrl(), request);
        String expectedResponseBody = readJson(testCasePath + "/response.json");
        compare(actualResponseActions, expectedStatus, expectedResponseBody);
    }

    protected void executeAndCompareDelete(HttpStatus expectedStatus) throws Exception {
        String testCasePath = testCaseBase + getFolderName();
        ResultActions actualResponseActions = executeDelete(base + getUrl());
        String expectedResponseBody = readJson(testCasePath + "/response.json");
        compare(actualResponseActions, expectedStatus, expectedResponseBody);
    }

    protected void executeAndComparePut(HttpStatus expectedStatus) throws Exception {
        String testCasePath = testCaseBase + getFolderName();
        String url = base + getUrl();
        String requestBody = readJson(testCasePath + "/request.json");
        ResultActions actualResponseActions = executePut(url, requestBody);
        String expectedResponseBody = readJson(testCasePath + "/response.json");
        compare(actualResponseActions, expectedStatus, expectedResponseBody);
    }

    private ResultActions executeGet(String url) throws Exception{
        return execute(get(url));
    }

    private ResultActions executePost(String url, String body) throws Exception {
        return execute(
                post(url)
                        .content(body)
                        .header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )
        );
    }

    private ResultActions executeDelete(String url) throws Exception {
        return execute(
                delete(url)
                        .header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )
        );
    }

    private ResultActions executePut(String url, String body) throws Exception {
        return execute(
                put(url).content(body)
                        .header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )
        );
    }

    private ResultActions execute(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    private void compare(ResultActions resultActions, HttpStatus expectedStatus, String expectedResponseBody) throws Exception{
        String actualResponseBody = compareStatus(resultActions, expectedStatus);
        if (expectedStatus != HttpStatus.UNAUTHORIZED) {
            compareResponseBody(expectedResponseBody, actualResponseBody);
        }
    }

    //compare response status and return response body as string
    private String compareStatus(ResultActions actions, HttpStatus expectedStatus) throws Exception {
        ResultMatcher resultMatcher = switch (expectedStatus) {
            case CONFLICT: yield status().isConflict();
            case UNAUTHORIZED: yield status().isUnauthorized();
            case NOT_FOUND : yield status().isNotFound();
            case BAD_REQUEST: yield status().isBadRequest();
            default : yield status().isOk();
        };
        return actions.
                andExpect(resultMatcher)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private void compareResponseBody(String expectedResponse, String actualResponse) {
            assertJson(actualResponse).where()
                    .keysInAnyOrder()
                    .arrayInAnyOrder()
                    .isEqualTo(expectedResponse);
    }

    protected abstract String getUrl();
    protected abstract String getFolderName();
    public abstract void execute() throws Exception;
}
