package com.bit.steps.APISteps;

import com.bit.context.TestContext;
import com.bit.objects.api.jsonplaceholder.Post;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonApiSteps {
    private TestContext testContext;

    private Response response;
    private Post expectedPost;

    public CommonApiSteps(TestContext testContext) {
        this.testContext = testContext;
    }
    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expected) {
        response = testContext.getResponse();
        System.out.println("Response body: " + response.getBody().asString());
        assertEquals(expected,response.getStatusCode());
    }










}
