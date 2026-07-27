package com.bit.ui;

import com.bit.utils.ConfigReader;

public class CLIPage extends BasePage{
    public void openCLIPage(){
        navigate(ConfigReader.get("ui.baseUrl")+"/agent-cli/introduction");
    }

}
