package com.bit.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public abstract class BaseApi {

    protected RequestSpecification request(String baseUrl) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured());
    }

    protected RequestSpecification getAuthRequestSpec(String baseUrl,String token) {
        return request(baseUrl)
                .header("Authorization", "Bearer " + token);
    }
}
