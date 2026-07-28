package com.bit.steps.SaucedemoSteps;

import com.bit.context.TestContext;
import com.bit.ui.HomePage;
import com.bit.ui.Saucedemo.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageStepsDefinition {
    public LoginPageStepsDefinition(TestContext context) {
        this.context = context;
    }

    private final TestContext context;
    LoginPage loginPage = new LoginPage();



    @Given("I open SauceDemo login page")
    public void iOpenLoginPage() {
      loginPage.open();
      context.setCurrentPage(loginPage);
    }


//    @Then("the page title should contain {string}")
//    public void thePageTitleShouldContain(String expected) {
//        String title =context.getCurrentPage().title();
//        assertTrue(title.contains(expected),
//                "Expected title to contain '" + expected + "' but was '" + title + "'");
//    }
//    @When("user login as user {string} and password {string}" )
//    public void loginWithCreds(String username,String password) {
//        loginPage.
//    }

}
