package com.bit.steps;

import com.bit.ui.DocsPage;
import com.bit.ui.HomePage;
import com.bit.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiSteps {
    private HomePage homePage;
    private DocsPage docsPage;
    private final TestContext context;

    public UiSteps(TestContext context) {
        this.context = context;
    }

    @Given("I open the UI home page")
    public void iOpenTheUiHomePage() {
        homePage = new HomePage();
        homePage.open();
        context.setCurrentPage(homePage);
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expected) {
        String title =context.getCurrentPage().title();
        assertEquals(expected, title, "Expected title to contain '" + expected + "' but was '" + title + "'");
    }

    @Given("I open Docs page")
    public void openDocsPage(){
        docsPage=new DocsPage();
        docsPage.openDocsPage();
        context.setCurrentPage(docsPage);
    }
}
