package com.bit.api;

import com.bit.utils.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Base class for REST Assured API tests. Builds a request specification
 * pre-configured with the API base URI and the Allure reporting filter.
 */
public abstract class BaseApi {

    protected RequestSpecification request() {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("api.baseUrl"))
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured());
    }
}
