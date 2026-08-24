package com.bit.steps.SaucedemoSteps;

import com.bit.ui.Saucedemo.InventoryPage;
import io.cucumber.java.en.When;

public class InventoryPageStepsDefinition {
    InventoryPage inventoryPage = new InventoryPage();

    @When("I add backpack to shopping card")
    public void addBackpachtoShoppingCard(){
        inventoryPage.addBackpackToCart();
    }

}
