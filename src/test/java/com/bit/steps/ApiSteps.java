package com.bit.steps;

import com.bit.api.BaseApi;
import com.bit.objects.api.Post;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiSteps extends BaseApi {

    private Response response;
    private Post expectedPost;
    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String path) {
        response = request().when().get(path).andReturn();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expected) {
        assertEquals(expected, response.getStatusCode());
    }

    @And("the response field {string} should equal {string}")
    public void theResponseFieldShouldEqual(String field, Object expected) {
        String actual = response.jsonPath().getString(field);
        assertEquals(expected, actual);
    }

    @And("the response field {string} should equal {int}")
    public void theResponseIntFieldShouldEqual(String field, int expected) {
        int actual = response.jsonPath().getInt(field);
        assertEquals(expected, actual);
    }

    @When("I send a POST request to {string}")
    public void iSendAPostRequestTo(String path) {
       response = request()
               .contentType(ContentType.JSON)
               .body(Post.builder().userId(90).title("Test").body("Some random body").build())
       .when()
               .post(path)
               .andReturn();
    }

    @When("I send a POST request to {string} with title {string}, body {string} and userId {int}")
    public void iSendAPostRequestToWithData(String path, String title, String body, int userId) {
        expectedPost = Post.builder()
                .title(title)
                .body(body)
                .userId(userId)
                .build();
        response = request()
                .contentType(ContentType.JSON)
                .body(expectedPost)
                .when()
                .post(path)
                .andReturn();
    }

    @Then("the response body should match the sent post")
    public void theResponseBodyShouldMatchTheSentPost() {
        Post actualPost = response.as(Post.class);
        expectedPost.setId(actualPost.getId());
        assertEquals(expectedPost, actualPost);
    }


    @Then("the response body should match the title {string}, body {string} and userId {int}")
    public void theResponseBodyShouldMatchTheSentPostListOfParameters(String title, String body, int userId){
        Post actualPost = response.as(Post.class);
        Post expectedPostWithParameters = Post.builder().title(title).body(body).userId(userId).build();
        expectedPostWithParameters.setId(actualPost.getId());
        assertEquals(actualPost, expectedPostWithParameters);
    }
}
