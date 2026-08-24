package com.bit.steps.SaucedemoSteps;

import com.bit.context.TestContext;
import com.bit.objects.Sauce.SauceUserFactory;
import com.bit.objects.Sauce.SauceUserModel;
import com.bit.ui.Saucedemo.LoginPage;
import io.cucumber.java.en.Given;
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


    @When("user login as {string}")
    public void userLoginAs(String userRole) {
        LoginPage loginPage=getLoginPage();
        SauceUserModel sauceUserModel = SauceUserFactory.createSauceUser(userRole);
        loginPage.login(sauceUserModel);
    }
}
