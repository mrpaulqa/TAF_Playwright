package com.bit.api.jsonplaceholder;

import com.bit.api.BaseApi;
import com.bit.objects.api.jsonplaceholder.Post;
import com.bit.utils.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostsApiClient extends BaseApi {
    private static final String POSTS_ENDPOINT = "/posts";
    private static final String BASE_URL = ConfigReader.get("api.jsonplaceholderBaseUrl");

    // 1. Метод, возвращающий сырой Response (для проверок статус-кодов, заголовков)
    public Response createPost(Post postBody) {
        return given()
                .spec(request(BASE_URL))
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
                .spec(request(BASE_URL))
                .get(POSTS_ENDPOINT + "/" + userId);
    }

    public Response getAllPosts(int userId) {
        return given()
                .spec(request(BASE_URL))
                .get(POSTS_ENDPOINT);
    }

    public Response updatePostById(int postId,Post postBody) {
        return given()
                .spec(request(BASE_URL))
                .body(postBody)
                .put(POSTS_ENDPOINT);
    }

    public Response patchPost(int postId,Post postBody) {
        return given()
                .spec(request(BASE_URL))
                .body(postBody)
                .put(POSTS_ENDPOINT);
    }

    public Response deletePost(int postId) {
        return given()
                .spec(request(BASE_URL))
                .delete(POSTS_ENDPOINT + "/"+postId);
    }




}
