package com.bit.api.specs;

import com.bit.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class UnauthSpec implements RequestSpecificationInt {
    @Override
    public RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("api.jsonplaceholderBaseUrl"))
                .setContentType(ContentType.JSON)
                .build();
    }
}
