package com.bit.ui.Saucedemo;

import com.bit.ui.BasePage;
import com.bit.utils.ConfigReader;

public class LoginPage extends BasePage {
//    private final Locator usernameInput =
//    private final Locator passwordInput = ;
//    private final Locator loginButton = page.getByText("Login");

    public void open() {
        navigate(ConfigReader.get("ui.secondaryURL"));
    }

    public void login(String username,String password){
        typeText(page.getByPlaceholder("Username"),username);
        typeText(page.getByPlaceholder("Password"),password);
        page.getByText("Login").click();
    }


}
