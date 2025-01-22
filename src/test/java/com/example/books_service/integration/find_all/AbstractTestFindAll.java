package com.example.books_service.integration.find_all;

import com.example.books_service.integration.AbstractTestRestController;

public abstract class AbstractTestFindAll extends AbstractTestRestController {
    @Override
    protected String getUrl() {
        return "/find";
    }

    @Override
    protected String getFolderName() {
        return "find_all";
    }
}
