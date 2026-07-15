package com.bit.steps;

import com.bit.ui.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiSteps {

    private HomePage homePage;

    @Given("I open the UI home page")
    public void iOpenTheUiHomePage() {
        homePage = new HomePage();
        homePage.open();
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expected) {
        String title = homePage.title();
        assertTrue(title.contains(expected),
                "Expected title to contain '" + expected + "' but was '" + title + "'");
    }
}
