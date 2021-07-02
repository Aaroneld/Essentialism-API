package com.merrifield.Essentialism.API;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.codehaus.jackson.JsonProcessingException;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public final class IntegrationTestHelper implements ExpectedTestData{

    static String getAuthorizationToken(int port, String baseUri){

        baseURI = baseUri;
        String unencodedToken = String.format("%s:%s", System.getenv("OAUTHCLIENTID"), System.getenv("OAUTHCLIENTSECRET"));

       Response response = given().port(port)
        .header("Authorization", String.format("Basic %s", Base64.getEncoder().encodeToString(unencodedToken.getBytes())))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .param("grant_type", "password")
        .param("username", "test")
        .param("password", "test")
        .when()
        .post("/login")
        .then()
        .extract()
        .response();

        return response.path("access_token").toString();
    }

    static <E> List<String> stringifyTestData(Stream<E> testData, ObjectMapper  mapper){
        return testData.map(value -> {
            try {
                return mapper.writeValueAsString(value);
            } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                e.printStackTrace();
            }
            return "problem";
        }).collect(Collectors.toList());
    }
}
