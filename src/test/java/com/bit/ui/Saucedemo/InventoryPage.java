package com.bit.ui.Saucedemo;

import com.bit.ui.BasePage;
import com.bit.ui.Components.HeaderComponent;
import com.bit.utils.PlaywrightFactory;
import com.microsoft.playwright.Locator;

public class InventoryPage extends BasePage {
    private final Locator backPackLocator = page.locator("#add-to-cart-sauce-labs-backpack");

    HeaderComponent headerComponent = new HeaderComponent(PlaywrightFactory.getPage());

    public void addBackpackToCart(){
        backPackLocator.click();
    }

    public int getItemsCountfromSC(){
       return  headerComponent.getCartBadgeCount();
    }
}
