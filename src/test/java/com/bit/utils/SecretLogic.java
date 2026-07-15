package com.bit.utils;

import org.junit.jupiter.api.extension.*;

public class SecretLogic implements BeforeTestExecutionCallback, AfterTestExecutionCallback {


    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

        System.out.println("Secret logic executed after test.");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {

        System.out.println("Secret logic executed before test.");
    }
}
