package com.bit.ui;

import com.bit.utils.ConfigReader;

public class MCPPage extends BasePage{
    public void openMCPPage(){
        navigate(ConfigReader.get("ui.baseUrl")+"/mcp/introduction");
    }

}
