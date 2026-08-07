package com.bit.ui.Saucedemo;

import com.bit.objects.Sauce.SauceUserModel;
import com.bit.ui.BasePage;
import com.bit.utils.ConfigReader;


public class LoginPage extends BasePage {

    public void open() {
        navigate(ConfigReader.get("ui.secondaryURL"));
    }

    public void login(SauceUserModel sauceUserModel){
        typeText(page.getByPlaceholder("Username"),sauceUserModel.getUsername(),"Username field");
        typeText(page.getByPlaceholder("Password"),sauceUserModel.getPassword(),"Password field");
        clickWithRetry(page.getByText("Login"),"Login");
    }


}
