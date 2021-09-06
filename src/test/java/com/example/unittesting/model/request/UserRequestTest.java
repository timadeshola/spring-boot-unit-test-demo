package com.example.unittesting.model.request;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

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
class UserRequestTest {

    private final Faker faker = new Faker();
    private UserRequest request;

    @BeforeEach
    public void setUp() {
        request = UserRequest.builder().build();
    }

    @Test
    void testUserRequestModel() {
        request = UserRequest.builder()
                .username("timadeshola")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        assertNotNull(request);
        assertEquals("timadeshola", request.getUsername());
        assertThat(request).isExactlyInstanceOf(UserRequest.class);
    }
}