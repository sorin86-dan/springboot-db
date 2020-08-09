package com.testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@SpringBootTest
public class DefaultDBTest {

    @Test
    public void checkMessageEndpoint() {
        RestAssured.baseURI = "http://localhost:8080";
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("id", "OK")
                .body("{\"db\":\"Redis\"}")
                .post("/db-message");

        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("The chosen database is: Redis"));
    }

    @Test
    public void checkMessageEndpointMissingHeader() {
        RestAssured.baseURI = "http://localhost:8080";
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"db\":\"Redis\"}")
                .post("/db-message");

        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.getBody().asString().contains("\"error\":\"Bad Request\""));
    }

    @Test
    public void checkMessageEndpointWrongHeader() {
        RestAssured.baseURI = "http://localhost:8080";
        Response response = RestAssured.given()
                .header("id", "NOK")
                .header("Content-Type", "application/json")
                .body("{\"db\":\"Redis\"}")
                .post("/db-message");

        assertEquals(response.getStatusCode(), 401);
        assertEquals(response.getBody().asString(), "Authorization failed");
    }

}