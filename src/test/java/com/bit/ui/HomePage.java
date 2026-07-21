package com.bit.ui;

import com.bit.utils.ConfigReader;
import com.microsoft.playwright.Page;

/**
 * Sample page object for the UI base URL configured in {@code config.properties}.
 */
public class HomePage extends BasePage {

    public void open() {
        navigate(ConfigReader.get("ui.baseUrl"));
    }
}
