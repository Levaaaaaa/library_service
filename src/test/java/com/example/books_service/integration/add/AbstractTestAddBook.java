package com.example.books_service.integration.add;

import com.example.books_service.integration.AbstractTestRestController;

public abstract class AbstractTestAddBook extends AbstractTestRestController {
    @Override
    protected String getUrl() {
        return "/add";
    }

    @Override
    protected String getFolderName() {
        return "add";
    }
}
