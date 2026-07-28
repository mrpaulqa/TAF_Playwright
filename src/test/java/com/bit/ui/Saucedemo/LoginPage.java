package com.bit.ui.Saucedemo;

import com.bit.ui.BasePage;
import com.bit.utils.ConfigReader;
import com.microsoft.playwright.Locator;

public class LoginPage extends BasePage {
    private final Locator username = page.getByPlaceholder("Username");
    private final Locator password = page.getByPlaceholder("Password");

    public void open() {
        navigate(ConfigReader.get("ui.secondaryURL"));
    }


}
