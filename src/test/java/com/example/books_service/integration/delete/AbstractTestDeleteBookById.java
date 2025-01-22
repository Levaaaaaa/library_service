package com.example.books_service.integration.delete;

import com.example.books_service.integration.AbstractTestRestController;

public abstract class AbstractTestDeleteBookById extends AbstractTestRestController {
    @Override
    protected String getUrl() {
        return "/delete";
    }

    @Override
    protected String getFolderName() {
        return "delete";
    }
}
