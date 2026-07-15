package com.bit.steps;

import com.bit.api.BaseApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiSteps extends BaseApi {

    private Response response;

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String path) {
        response = request().when().get(path).andReturn();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expected) {
        assertEquals(expected, response.statusCode());
    }

    @And("the response field {string} should equal {int}")
    public void theResponseFieldShouldEqual(String field, int expected) {
        assertEquals(expected, response.jsonPath().getInt(field));
    }
}
