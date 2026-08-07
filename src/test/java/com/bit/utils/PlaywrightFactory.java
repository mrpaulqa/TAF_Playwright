package com.bit.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.bit.utils.BrowserFactory.getBrowser;

/**
 * Owns the Playwright lifecycle for a scenario. The instances are kept in
 * {@link ThreadLocal}s so scenarios stay isolated if executed in parallel.
 * Created/destroyed from the Cucumber {@code @Before}/{@code @After} hooks.
 */
public final class PlaywrightFactory {

    private static final ThreadLocal<Playwright> PLAYWRIGHT = new ThreadLocal<>();
    private static final ThreadLocal<Browser> BROWSER = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> CONTEXT = new ThreadLocal<>();
    private static final ThreadLocal<Page> PAGE = new ThreadLocal<>();

    private PlaywrightFactory() {
    }

    public static Page initBrowser() {
        Playwright playwright = Playwright.create();
        PLAYWRIGHT.set(playwright);
        String browserType = ConfigReader.get("browser");
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(ConfigReader.getBoolean("ui.headless"));
        Browser browser =getBrowser(browserType).launch(playwright,launchOptions);
        BROWSER.set(browser);

        BrowserContext context = browser.newContext();
        CONTEXT.set(context);

        Page page = context.newPage();
        PAGE.set(page);
        return page;
    }

    public static Page getPage() {
        return PAGE.get();
    }

    public static void closeBrowser() {
        if (CONTEXT.get() != null) {
            CONTEXT.get().close();
        }
        if (BROWSER.get() != null) {
            BROWSER.get().close();
        }
        if (PLAYWRIGHT.get() != null) {
            PLAYWRIGHT.get().close();
                }
        PAGE.remove();
        CONTEXT.remove();
        BROWSER.remove();
        PLAYWRIGHT.remove();
    }
}
