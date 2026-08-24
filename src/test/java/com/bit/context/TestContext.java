package com.bit.context;

import com.bit.ui.BasePage;
import io.restassured.response.Response;

public class TestContext {
    private BasePage currentPage;
    private Response response;

    public BasePage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(BasePage page) {
        this.currentPage = page;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
