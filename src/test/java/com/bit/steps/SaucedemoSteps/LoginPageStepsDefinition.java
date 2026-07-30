package com.bit.steps.SaucedemoSteps;

import com.bit.context.TestContext;
import com.bit.ui.Saucedemo.LoginPage;
import com.bit.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepsDefinition {
    private final TestContext context;

    public LoginPageStepsDefinition(TestContext context) {
        this.context = context;
    }
    private LoginPage getLoginPage(){
        return new LoginPage();
    }
    @Given("I open SauceDemo login page")
    public void iOpenLoginPage() {
        LoginPage loginPage=getLoginPage();
        loginPage.open();
        context.setCurrentPage(loginPage);
    }

    @Then("user login as user {string} and password {string}" )
    public void loginWithCreds(String username,String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username,password);
    }


    @When("user login as {string}")
    public void userLoginAs(String userRole) {
        LoginPage loginPage=getLoginPage();
        String username = ConfigReader.get(userRole + ".username");
        System.out.println("Username: "+username);
        String password = ConfigReader.get(userRole + ".password");
        System.out.println("password: "+password);
        loginPage.login(username, password);
    }
}
