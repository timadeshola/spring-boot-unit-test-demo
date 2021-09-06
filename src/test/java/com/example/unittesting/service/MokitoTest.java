package com.example.unittesting.service;

import com.example.unittesting.model.request.UserRequest;
import com.example.unittesting.model.response.AppResponse;
import com.example.unittesting.model.response.UserResponse;
import com.example.unittesting.resource.UserResource;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static com.example.unittesting.core.AppConstants.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/7/21
 * Time: 12:05 AM
 */
@ExtendWith(MockitoExtension.class)
class MokitoTest {

    private final Faker faker = new Faker();
    @Mock
    private UserService userService;
    @InjectMocks
    private UserResource userResource;
    private UserRequest request;
    private UserResponse userResponse;

    @BeforeEach
    public void setUp() {
        request = UserRequest.builder()
                .username("timadeshola")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();
        UserResponse user = userService.createUser(request);
        lenient().when(user).thenReturn(user);
        userResponse = user;
    }

    @Test
    void testMokito() {
        ResponseEntity<AppResponse<UserResponse>> response = userResource.fetchUser(request.getUsername());
        assertThat(Objects.requireNonNull(response.getBody()).getMessage()).isEqualTo(SUCCESS);
    }
}