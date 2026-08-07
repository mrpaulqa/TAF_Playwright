package com.bit.api.jsonplaceholder;

import com.bit.api.BaseApi;
import com.bit.api.ThinkingTester.SpectType;
import com.bit.objects.api.jsonplaceholder.Post;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.bit.api.specs.RequestSpecFactory.getRequestSpecification;
import static io.restassured.RestAssured.given;

public class PostsApiClient extends BaseApi {
    private static final String POSTS_ENDPOINT = "/posts";
    private final RequestSpecification requestSpecification =getRequestSpecification(SpectType.UNAUTH);
    // 1. Метод, возвращающий сырой Response (для проверок статус-кодов, заголовков)
    public Response createPost(Post postBody) {
        return given()
                .spec(requestSpecification)
                .body(postBody)
                .post(POSTS_ENDPOINT);
    }

    // 2. Метод с автоматической десериализацией в DTO
    public Post getPostResponse(Response  response) {
        return response
                .then()
                .extract()
                .as(Post.class); // Jackson автоматизирует конвертацию!
    }

    public Response getPostById(int userId) {
        return given()
                .spec(requestSpecification)
                .get(POSTS_ENDPOINT + "/" + userId);
    }

    public Response getAllPosts(int userId) {
        return given()
                .spec(requestSpecification)
                .get(POSTS_ENDPOINT);
    }

    public Response updatePostById(int postId,Post postBody) {
        return given()
                .spec(requestSpecification)
                .body(postBody)
                .put(POSTS_ENDPOINT);
    }

    public Response patchPost(int postId,Post postBody) {
        return given()
                .spec(requestSpecification)
                .body(postBody)
                .put(POSTS_ENDPOINT);
    }

    public Response deletePost(int postId) {
        return given()
                .spec(requestSpecification)
                .delete(POSTS_ENDPOINT + "/"+postId);
    }




}
