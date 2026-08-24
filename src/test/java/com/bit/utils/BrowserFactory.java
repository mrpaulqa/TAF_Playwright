package com.bit.utils;

public class BrowserFactory {
    public static BrowserTypeInt getBrowser(String browserName) {
        if (browserName == null) {
            throw new IllegalArgumentException("Browser name cannot be null!");
        }
        switch (browserName.toLowerCase().trim()) {
            case "chrome":
            case "chromium":
                return new ChromiumDriverManager();
            case "firefox":
                return new FireFoxDriverManager();
            case "webkit":
            case "safari":
                return new WebKitDriverManager();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
    }

