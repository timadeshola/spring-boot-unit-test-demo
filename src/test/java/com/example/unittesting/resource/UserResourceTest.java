package com.example.unittesting.resource;

import com.example.unittesting.core.AppUtils;
import com.example.unittesting.model.request.UserRequest;
import com.example.unittesting.service.UserService;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.unittesting.core.AppConstants.SUCCESS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 6:52 PM
 */
@WebMvcTest(UserResource.class)
@Profile("test")
class UserResourceTest {

    private final Faker faker = new Faker();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserRequest request;

    @BeforeEach
    public void setUp() {
        request = UserRequest.builder().build();
    }

    @SneakyThrows
    @Test
    void testCreateUserEndpoint() {
        request = UserRequest.builder()
                .username("timadeshola")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(AppUtils.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(SUCCESS))
                .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @SneakyThrows
    @Test
    void testUpdateUserEndpoint() {
        request = UserRequest.builder()
                .username("timadeshola")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        mockMvc.perform(put("/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(AppUtils.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(SUCCESS))
                .andExpect(jsonPath("$.status").value("OK"));
    }

    @SneakyThrows
    @Test
    void testDeleteUserEndpoint() {
        mockMvc.perform(delete("/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(false));

    }

    @SneakyThrows
    @Test
    void testFetchUserByIdEndpoint() {
        mockMvc.perform(get("/users/fetchById/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(SUCCESS));

    }

    @SneakyThrows
    @Test
    void testFetchUserByUsernameEndpoint() {
        mockMvc.perform(get("/users/fetchByUsername/{username}", "timadeshola")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(SUCCESS));
    }

    @SneakyThrows
    @Test
    void testFetchUserByUsersEndpoint() {
        mockMvc.perform(get("/users/viewAll")
                        .queryParam("start", "0")
                        .queryParam("limit", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(SUCCESS));
    }

}