package com.example.books_service.integration.update;

import com.example.books_service.integration.AbstractTestRestController;

public abstract class AbstractTestUpdate extends AbstractTestRestController {
    @Override
    protected String getUrl() {
        return "/update";
    }

    @Override
    protected String getFolderName() {
        return "update";
    }
}
