package com.bit.ui.Components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HeaderComponent {
    private Page page;
    private final Locator header;
    private final Locator menu ;
    private final Locator shoppingCart;
    private final Locator checkoutButton;

    public HeaderComponent(Page page){
        this.page=page;
        this.header = page.locator(".title");
        this.shoppingCart = page.locator("#shopping_cart_container");
        this.menu =page.locator("#react-burger-menu-btn");
        this.checkoutButton= page.locator("#checkout");
    }


    public  String pageHeader() {
        return page.locator(".title").textContent();

    }
    public void openShoppingCart(){
        shoppingCart.click();
        checkoutButton.waitFor();
    }

    public int getCartBadgeCount(){
        if (shoppingCart.isVisible()) {
            return Integer.parseInt(shoppingCart.textContent().trim());
        }
        return 0;
    }
}