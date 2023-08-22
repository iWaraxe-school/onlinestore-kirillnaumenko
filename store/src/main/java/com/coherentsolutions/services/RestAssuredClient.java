package com.coherentsolutions.services;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;

public class RestAssuredClient {
    public RestAssured restClient;
    private RestAssuredClient()
    {
        // Set up Rest assured with url and authentication
        // P.S. for some reason this class cannot load Configuration class with xml config file, so values are hardcoded
        restClient.baseURI = "http://localhost:8081";
        String user = "admin";
        String password = "password";
        PreemptiveBasicAuthScheme authenticationScheme = new PreemptiveBasicAuthScheme();
        authenticationScheme.setUserName(user);
        authenticationScheme.setPassword(password);

        RestAssured.authentication = authenticationScheme;
    }

    // Since Java class loader is thread safe, this code should provide lazy initialization
    private static class HttpClientLoader {
        private static final RestAssuredClient INSTANCE;

        static {
                INSTANCE = new RestAssuredClient();
        }
    }

    public static RestAssuredClient getInstance() {

        return HttpClientLoader.INSTANCE;
    }
}
