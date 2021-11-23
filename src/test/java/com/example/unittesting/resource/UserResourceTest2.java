package com.example.unittesting.resource;

import com.example.unittesting.model.request.UserRequest;
import com.example.unittesting.model.response.AppResponse;
import com.example.unittesting.model.response.UserResponse;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 11/23/21
 * Time: 7:47 AM
 */
@SpringBootTest
class UserResourceTest2 {

    private final Faker faker = new Faker();
    @Autowired
    private UserResource userResource;
    private UserRequest userRequest;

    @BeforeEach
    public void setUp() {
        userRequest = UserRequest.builder().build();
    }

    @Test
    void testUserCreate() {
        userRequest = UserRequest.builder()
                .username("timadeshola")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();
        ResponseEntity<AppResponse<UserResponse>> response = userResource.createUser(userRequest);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getBody()).isInstanceOf(AppResponse.class);
        assertThat(response.getBody().getData()).isInstanceOf(UserResponse.class);
        assertThat(response.getBody().getData().getUsername()).isEqualTo("timadeshola");
    }

    @Test
    void testUserUpdate() {
        userRequest = UserRequest.builder()
                .username("timiadeshola")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();
        ResponseEntity<AppResponse<UserResponse>> response = userResource.updateUser(userRequest, 1L);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getBody()).isInstanceOf(AppResponse.class);
        assertThat(response.getBody().getData()).isInstanceOf(UserResponse.class);
        assertThat(response.getBody().getData().getUsername()).isEqualTo("timiadeshola");
    }

    @Test
    void testDeleteUser() {
        ResponseEntity<AppResponse<Boolean>> response = userResource.deleteUser(1L);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getBody()).isInstanceOf(AppResponse.class);
        assertThat(response.getBody().getData()).isInstanceOf(Boolean.class);
        assertThat(response.getBody().getData().booleanValue()).isTrue();
    }

}
