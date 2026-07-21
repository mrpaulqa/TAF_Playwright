package com.bit.ui;

import com.bit.utils.PlaywrightFactory;
import com.microsoft.playwright.Page;

/**
 * Base class for all Playwright page objects. Exposes the active {@link Page}
 * (managed by the Cucumber hooks) plus a few common helpers.
 */
public abstract class BasePage {

    protected final Page page;

    public BasePage() {
        this.page = PlaywrightFactory.getPage();
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public String title() {
        return this.page.title();
    }
}
