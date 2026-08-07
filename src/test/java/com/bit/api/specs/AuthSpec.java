package com.bit.api.specs;

import com.bit.utils.AuthManager;
import com.bit.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AuthSpec implements RequestSpecificationInt {
    @Override
    public RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("api.thinkingTester"))
                .addHeader("Authorization", "Bearer " + AuthManager.getToken())
                .setContentType(ContentType.JSON)
                .build();
    }
}
