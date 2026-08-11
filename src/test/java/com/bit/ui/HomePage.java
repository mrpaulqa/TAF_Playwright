package com.bit.ui;

import com.bit.utils.ConfigReader;
/**
 * Sample page object for the UI base URL configured in {@code config.properties}.
 */
public class HomePage extends BasePage {

    public void open() {
        navigate(ConfigReader.get("ui.baseUrl"));
    }
}
