package com.bit.context;

import com.bit.ui.BasePage;

public class TestContext {
    private BasePage currentPage;

    public BasePage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(BasePage page) {
        this.currentPage = page;
    }
}
