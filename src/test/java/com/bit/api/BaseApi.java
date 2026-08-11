package com.bit.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Base class for REST Assured API tests. Builds a request specification
 * pre-configured with the API base URI and the Allure reporting filter.
 */
public abstract class BaseApi {

    protected RequestSpecification request(String baseUrl) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured());
    }


    // Authorization specifier (if a Bearer Token is required)
    protected RequestSpecification getAuthRequestSpec(String baseUrl,String token) {
        return request(baseUrl)
                .header("Authorization", "Bearer " + token);
    }
}
