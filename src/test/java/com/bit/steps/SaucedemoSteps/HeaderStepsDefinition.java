package com.bit.steps.SaucedemoSteps;

import com.bit.ui.Components.HeaderComponent;
import com.bit.utils.PlaywrightFactory;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeaderStepsDefinition {

    private HeaderComponent getHeader() {
        return new HeaderComponent(PlaywrightFactory.getPage());
    }
    @Then("I check that page title is {string}")
    public void verifyHeader(String expectedHeaderTextContent){
        String header =getHeader().pageHeader();
        assertEquals(header, expectedHeaderTextContent, "Expected header to contain '"
                + expectedHeaderTextContent + "' but was '" + header + "'");
    }

    @Then("verify that items count in the shopping card equals {int}")
    public void verifyItemsCount(int expectedItemsNumber){
        int actualCount = getHeader().getCartBadgeCount();
        assertEquals(actualCount, expectedItemsNumber, "Expected items count '"
                + expectedItemsNumber + "' but was '" + actualCount + "'");
    }

}
