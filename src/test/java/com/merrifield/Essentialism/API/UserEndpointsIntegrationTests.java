package com.merrifield.Essentialism.API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merrifield.Essentialism.API.models.UserModels.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static com.merrifield.Essentialism.API.IntegrationTestHelper.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserEndpointsIntegrationTests implements ExpectedTestData {

    @LocalServerPort
    private int port;
    private String authToken;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    static void setup(){
        baseURI = "http://localhost";
    }

    @BeforeEach
    void getToken(){
        authToken = getAuthorizationToken(port, baseURI);
    }


    @Test
    void whenUsersRequestsAllUsers_thenOK() throws Exception{

        String response = given()
                            .port(port)
                            .header("Authorization", String.format("Bearer %s", authToken))
                                .when()
                                .get("/users")
                                    .then()
                                    .statusCode(200)
                                    .contentType("application/json")
                                    .extract()
                                    .asString();

        List<User> responseUsers =  objectMapper.readValue(response, new TypeReference<List<User>>() {});
        assert(responseUsers.size() == TEST_USERS.size());
    }

    @Test
    void whenUsersSearchesForValidUserById_thenOK() throws Exception {
        given()
            .port(port)
            .header("Authorization", String.format("Bearer %s", authToken))
            .contentType("application/json")
                .when()
                .get("/users/11")
                    .then()
                    .statusCode(200)
                    .contentType("application/json")
                    .body("id", equalTo(11))
                    .body("username", equalTo("aaroneld"))
                    .body("firstName", equalTo("Aaron"))
                    .body("lastName", equalTo("Merrifield"))
                    .body("email", equalTo("merrifield48@gmail.com"))
                    .body("$", not(hasKey("password")));
   }

    @Test
    void whenUsersSearchesForInvalidUserById_then404() throws Exception {
        given()
                .port(port)
                .header("Authorization", String.format("Bearer %s", authToken))
                .contentType("application/json")
                    .when()
                    .get("/users/1000")
                        .then()
                        .statusCode(404)
                        .contentType("application/json")
                        .body(equalTo("User with id 1000 not found"));

    }

    @Test
    void whenClientAttemptsPostAtUsersEndpoint_Then405() throws Exception {
        User user = FUNCTIONAL_TEST_USER;

        given()
        .port(port)
        .header("Authorization", String.format("Bearer %s", authToken))
        .body(objectMapper.writeValueAsString(user))
        .contentType("application/json")
            .when()
            .post(String.format("/users/%s", user.getId()))
                .then()
                .statusCode(405);

    }


    @Test
    void whenTestUserUpdatesOwnInfo_Then201() throws Exception {

        User user = FUNCTIONAL_TEST_USER;
        user.setEmail("newEmail@email.com");
        user.setFirstName("NewFirstName");

        String response = given()
                            .port(port)
                            .header("Authorization", String.format("Bearer %s", authToken))
                            .body(objectMapper.writeValueAsString(user))
                            .contentType("application/json")
                                .when()
                                .put(String.format("/users/%s", user.getId()))
                                    .then()
                                    .statusCode(202)
                                    .contentType("application/json")
                                    .extract()
                                    .asString();

        assert(response.equals(String.valueOf(user.getId())));

    }

    @Test
    void whenTestUserAttemptsToUpdateOtherUsersInfo_Then403() throws Exception {

        User user = FUNCTIONAL_TEST_USER;

        given()
            .port(port)
            .header("Authorization", String.format("Bearer %s", authToken))
            .body(objectMapper.writeValueAsString(user))
            .contentType("application/json")
                .when()
                .put(String.format("/users/%s", 1))
                    .then()
                    .statusCode(403)
                    .contentType("application/json")
                    .body(equalTo(String.format("User %s is not authorized to update user with id of 1", user.getUsername())));

    }
}
