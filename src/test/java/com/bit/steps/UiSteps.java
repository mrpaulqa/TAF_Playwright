package com.bit.steps;

import com.bit.ui.DocsPage;
import com.bit.ui.HomePage;
import com.bit.context.TestContext;
import com.bit.ui.MCPPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiSteps {
    private final TestContext context;

    public UiSteps(TestContext context) {
        this.context = context;
    }

    @Given("I open the UI home page")
    public void iOpenTheUiHomePage() {
        HomePage homePage = new HomePage();
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
        DocsPage docsPage = new DocsPage();
        docsPage.openDocsPage();
        context.setCurrentPage(docsPage);
    }

    @Given("I open MCP page")
    public void openMCPPage(){
        MCPPage mcpPage = new MCPPage();
        mcpPage.openMCPPage();
        context.setCurrentPage(mcpPage);
    }
}
