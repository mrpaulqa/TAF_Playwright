package com.bit.api.specs;

import com.bit.api.ThinkingTester.SpectType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    public static RequestSpecification getRequestSpecification(SpectType spectType) {
        return switch (spectType) {
            case AUTH -> new AuthSpec().getSpecification();
            case UNAUTH -> new UnauthSpec().getSpecification();
            default-> throw new IllegalArgumentException("Invalid request specification name: " + spectType);
        };
    }
}
