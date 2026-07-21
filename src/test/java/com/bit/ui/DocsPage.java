package com.bit.ui;

import com.bit.utils.ConfigReader;

public class DocsPage extends BasePage{
    public void openDocsPage(){
        navigate(ConfigReader.get("ui.baseUrl")+"/docs/intro");
    }
}
