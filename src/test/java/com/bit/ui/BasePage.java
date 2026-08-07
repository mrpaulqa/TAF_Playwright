package com.bit.ui;

import com.bit.utils.PlaywrightFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all Playwright page objects. Exposes the active {@link Page}
 * (managed by the Cucumber hooks) plus a few common helpers.
 */
public abstract class BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    protected final Page page;

    public BasePage() {
        this.page = PlaywrightFactory.getPage();
    }

    public void navigate(String url) {
        logger.info("User opens URL: {}", url);
        page.navigate(url);
    }

    public String title() {
        String pageTitle = page.title();
        logger.info("User getting page title: {}", pageTitle);
        return pageTitle;
    }

    public String pageHeader() {
        return this.page.locator("h1").textContent();
    }

    public void typeText(Locator inputLocator, String text) {
        logger.info("User type text: {} in locator: {}", text, inputLocator);
        inputLocator.fill(text);
    }

    public void typeText(Locator inputLocator, String text, String elementName) {
        logger.info("User type text: {} in locator: {}", text, elementName);
        inputLocator.fill(text);
    }

    public String getText(Locator inputLocator) {
        logger.info("User get text from locator: {}", inputLocator);
        return inputLocator.textContent();
    }

    public Boolean isVisible(Locator inputLocator) {
        logger.info("User visibility: {}", inputLocator);
        return inputLocator.isVisible();
    }

    public void click(Locator locator) {
        logger.info("User clicks on element: {}", locator);
        locator.click();
    }

    public void click(Locator locator, String elementName) {
        logger.info("User clicks on: {}", elementName);
        locator.click();
    }

    public void clickWithRetry(Locator locator, String elementName) {
        clickWithRetry(locator, elementName, 3);
    }

    // 4. Полный метод Retry
    public void clickWithRetry(Locator locator, String elementName, int maxAttempts) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                logger.info("User trying to click on: {} (Attempt {})", elementName, attempts + 1);
                locator.click();
                return;
            } catch (Exception e) {
                attempts++;
                logger.warn("Attempt {} to click on '{}' failed. Retrying...", attempts, elementName);
                if (attempts == maxAttempts) {
                    logger.error("Failed to click on '{}' after {} attempts", elementName, maxAttempts);
                    throw e;
                }
            }
        }
    }
}
