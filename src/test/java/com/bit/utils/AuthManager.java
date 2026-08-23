package com.bit.utils;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class AuthManager {
    private static String token;

    public static synchronized String getToken() {
        if (token == null) {
            String email = ConfigReader.get("api.thinkingEmail");
            String password = ConfigReader.get("api.thinkingPassword");

            token = given()
                    .contentType(ContentType.JSON)
                    .body(String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password))
                    .when()
                    .post(ConfigReader.get("api.thinkingTester") + "/users/login")
                    .then()
                    .statusCode(200)
                    .extract()
                    .path("token"); // Извлекаем токен из JSON ответа {"token": "eyJhbG..."}
        }
        return token;
    }
}
