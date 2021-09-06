package com.example.unittesting.service;

import com.example.unittesting.core.ModelMapperUtils;
import com.example.unittesting.model.request.UserRequest;
import com.example.unittesting.model.response.PaginateResponse;
import com.example.unittesting.model.response.UserResponse;
import com.example.unittesting.persistence.entity.User;
import com.example.unittesting.persistence.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 6:18 PM
 */
@SpringBootTest
@Profile("test")
class UserServiceTest {

    private final Faker faker = new Faker();
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private User user;
    private UserRequest request;
    private UserResponse response;

    @BeforeEach
    public void setUp() {
        user = User.builder().build();
        request = UserRequest.builder().build();
        response = UserResponse.builder().build();
    }

    @Test
    void testCreateUser() {
        request = UserRequest.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        response = userService.createUser(request);

        user = ModelMapperUtils.map(response, user);

        assertThat(user.getEmail()).isEqualTo(response.getEmail());
        assertNotNull(user);
        assertNotNull(response);
        assertEquals(request.getUsername(), response.getUsername());
        assertEquals(user.getUsername(), response.getUsername());
        assertThat(response).isInstanceOf(UserResponse.class);
    }

    @Test
    void testUpdateUser() {
        request = UserRequest.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        response = userService.createUser(request);

        request.setEmail("timadeshola@gmail.com");
        request.setLastName("Adeshola");
        response = userService.updateUser(request, response.getId());

        user = ModelMapperUtils.map(response, user);

        assertEquals("timadeshola@gmail.com", user.getEmail());
        assertEquals("Adeshola", user.getLastName());
        assertThat(user.getEmail()).isEqualTo(response.getEmail());
        assertNotNull(user);
        assertNotNull(response);
        assertEquals(request.getUsername(), response.getUsername());
        assertThat(response).isInstanceOf(UserResponse.class);
    }

    @Test
    void testDeleteUser() {
        request = UserRequest.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        response = userService.createUser(request);

        Boolean deleteUser = userService.deleteUser(response.getId());

        assertThat(deleteUser).isTrue();
    }

    @Test
    void testFetchUserById() {
        request = UserRequest.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        response = userService.createUser(request);

        UserResponse foundResponse = userService.fetchUser(response.getId());

        user = ModelMapperUtils.map(foundResponse, user);

        assertThat(response.getEmpId()).isEqualTo(foundResponse.getEmpId());
        assertThat(user.getEmail()).isEqualTo(response.getEmail());
        assertNotNull(user);
        assertNotNull(response);
        assertEquals(request.getUsername(), response.getUsername());
        assertThat(response).isInstanceOf(UserResponse.class);
        assertThat(user).isInstanceOf(User.class);
    }

    @Test
    void testFetchUserByUsername() {
        request = UserRequest.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        response = userService.createUser(request);

        UserResponse foundResponse = userService.fetchUser(response.getUsername());

        user = ModelMapperUtils.map(foundResponse, user);

        assertThat(response.getEmpId()).isEqualTo(foundResponse.getEmpId());
        assertThat(user.getEmail()).isEqualTo(response.getEmail());
        assertNotNull(user);
        assertNotNull(response);
        assertEquals(request.getUsername(), response.getUsername());
        assertThat(response).isInstanceOf(UserResponse.class);
        assertThat(user).isInstanceOf(User.class);
    }

    @Test
    void testFetchUsers() {
        List<UserRequest> requests = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            request = UserRequest.builder()
                    .username(faker.name().username())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .build();
            requests.add(request);
        }

        List<User> users = ModelMapperUtils.mapAll(requests, User.class);

        List<User> userList = userRepository.saveAll(users);

        PaginateResponse<UserResponse> fetchUsers = userService.fetchUsers(0, 20);


        assertNotNull(users);
        assertNotNull(requests);
        assertNotNull(userList);
        assertNotNull(fetchUsers);
        assertThat(users).isInstanceOf(List.class);
        assertThat(fetchUsers.getContent()).isInstanceOf(List.class);
        assertThat(fetchUsers.getTotalElements()).isInstanceOf(Long.class);
        assertThat(fetchUsers.getContent().size()).isGreaterThanOrEqualTo(10);
    }


}