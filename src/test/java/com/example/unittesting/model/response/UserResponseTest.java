package com.example.unittesting.model.response;

import com.example.unittesting.model.enums.Status;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.sql.Timestamp;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 5:32 PM
 */
@SpringBootTest
@Profile("test")
class UserResponseTest {

    private final Faker faker = new Faker();
    private UserResponse response;

    @BeforeEach
    public void setUp() {
        response = UserResponse.builder().build();
    }

    @Test
    void testUserResponseModel() {
        response = UserResponse.builder()
                .id(1L)
                .username("timadeshola")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .empId(UUID.randomUUID().toString())
                .status(String.valueOf(Status.ACTIVE.getStatus()))
                .email(faker.internet().emailAddress())
                .dateCreated(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .dateUpdated(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build();

        assertNotNull(response);
        assertEquals("timadeshola", response.getUsername());
        assertThat(response).isExactlyInstanceOf(UserResponse.class);
    }
}