package com.bit.hooks;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ApiHooks {


    private WireMockServer wireMockServer;
    private final String tagValue="@NonExistTag";
    @Before(value=tagValue, order=20)
    public void beforeApiThinkingTesterCC() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        wireMockServer.stubFor(post(urlPathEqualTo("/users/login"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"token\": \"mocked-jwt-token-12345\"}")));

        wireMockServer.stubFor(post(urlPathEqualTo("/contacts"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"_id\": \"12345\", \"firstName\": \"John\"}")));
    }

    @After(value=tagValue)
    public void afterApiThinkingTesterCC() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

}
