package com.bit.steps.APISteps.jsonplaceholder;

import com.bit.api.jsonplaceholder.PostsApiClient;
import com.bit.context.TestContext;
import com.bit.objects.api.jsonplaceholder.Post;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostSteps {

    private final PostsApiClient postsApiClient=new PostsApiClient();
    private final TestContext testContext;

    private Response response;
    private Post expectedPost;

    public PostSteps(TestContext testContext) {
        this.testContext = testContext;
    }
    @When("I create post with hardcoded values")
    public void iSendAPostRequestTo() {
        expectedPost = Post.builder()
                .title("Test")
                .body("Some random body")
                .userId(90)
                .build();
        response = postsApiClient.createPost(expectedPost);
        testContext.setResponse(response);

    }

    @When("I get post by id {int}")
    public void getPostById(int postId) {
        response = postsApiClient.getPostById(postId);
        testContext.setResponse(response);

    }


    @When("I send a POST request to {string} with title {string}, body {string} and userId {int}")
    public void iSendAPostRequestToWithData(String path, String title, String body, int userId) {
        expectedPost = Post.builder()
                .title(title)
                .body(body)
                .userId(userId)
                .build();
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(expectedPost)
                .when()
                .post(path)
                .andReturn();
        testContext.setResponse(response);

    }
    //NEW
    @When("I create post with title {string}, body {string} and userId {int}")
    public void iCreatePostWithBody(String title, String body, int userId) {
        expectedPost = Post.builder()
                .title(title)
                .body(body)
                .userId(userId)
                .build();
        response = postsApiClient.createPost(expectedPost);
        testContext.setResponse(response);

    }

    @Then("the response body should match the sent post")
    public void theResponseBodyShouldMatchTheSentPost() {
        Post actualPost = response.as(Post.class);
        testContext.setResponse(response);

        expectedPost.setId(actualPost.getId());
        assertEquals(expectedPost, actualPost);
    }

    @Then("the response body should match the title {string}, body {string} and userId {int}")
    public void theResponseBodyShouldMatchTheSentPostListOfParameters(String title, String body, int userId){
        Post actualPost = response.as(Post.class);
        testContext.setResponse(response);
        Post expectedPostWithParameters = Post.builder().title(title).body(body).userId(userId).build();
        expectedPostWithParameters.setId(actualPost.getId());
        assertEquals(actualPost, expectedPostWithParameters);
    }
    //NEW
    @Then("the posts response body should match the title {string}, body {string} and userId {int}")
    public void thePostResponseBodyShouldMatchTheSentPostListOfParameters(String title, String body, int userId){
        Post actualPost = postsApiClient.getPostResponse(response);
        testContext.setResponse(response);
        Post expectedPostWithParameters = Post.builder().title(title).body(body).userId(userId).build();
        expectedPostWithParameters.setId(actualPost.getId());
        assertEquals(actualPost, expectedPostWithParameters);
    }

    @And("the response field {string} should equal {string}")
    public void theResponseFieldShouldEqual(String field, Object expected) {
        String actual = response.jsonPath().getString(field);
        testContext.setResponse(response);
        assertEquals(expected, actual);
    }

    @And("the response field {string} should equal {int}")
    public void theResponseIntFieldShouldEqual(String field, int expected) {
        int actual = response.jsonPath().getInt(field);
        testContext.setResponse(response);
        assertEquals(expected, actual);
    }
}
