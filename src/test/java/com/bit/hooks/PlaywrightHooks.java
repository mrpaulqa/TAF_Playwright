package com.bit.hooks;

import com.bit.utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Manages the Playwright lifecycle for UI scenarios only (tagged {@code @ui}),
 * so API/DB scenarios never spin up a browser. On failure a screenshot is
 * attached to the Allure/Cucumber report.
 */
public class PlaywrightHooks {

    @Before(value = "@ui", order = 10)
    public void launchBrowser() {
        PlaywrightFactory.initBrowser();
    }


    @After(value = "@ui", order = 10)
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed() && PlaywrightFactory.getPage() != null) {
            byte[] screenshot = PlaywrightFactory.getPage().screenshot(
                    new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(screenshot, "image/png", "Failed Step: " + scenario.getName());
        }
        PlaywrightFactory.closeBrowser();
    }


}
