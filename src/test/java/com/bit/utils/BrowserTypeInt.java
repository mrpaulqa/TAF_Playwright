package com.bit.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public interface BrowserTypeInt {
    Browser launch(Playwright playwright, BrowserType.LaunchOptions options);
}
