package com.bit.ui;

import com.bit.utils.ConfigReader;

public class HomePage extends BasePage {

    public void open() {
        navigate(ConfigReader.get("ui.baseUrl"));
    }
}
