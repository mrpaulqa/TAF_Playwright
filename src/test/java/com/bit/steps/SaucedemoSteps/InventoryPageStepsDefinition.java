package com.bit.steps.SaucedemoSteps;

import com.bit.ui.Saucedemo.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.internal.common.assertion.Assertion;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryPageStepsDefinition {
    InventoryPage inventoryPage = new InventoryPage();

    @When("I add backpack to shopping card")
    public void addBackpachtoShoppingCard(){
        inventoryPage.addBackpackToCart();
    }

}
