package com.example.unittesting.persistence.repository;

import com.example.unittesting.core.exceptions.CustomException;
import com.example.unittesting.model.enums.Status;
import com.example.unittesting.persistence.entity.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 5:48 PM
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Profile("test")
class UserRepositoryTest {

    private final Faker faker = new Faker();
    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder().build();
    }

    @Test
    @DisplayName("Save User - Repository Test")
    void testSaveUser() {
        user = User.builder()
                .id(1L)
                .username("timadeshola")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .empId(UUID.randomUUID().toString())
                .status(Status.ACTIVE.getStatus())
                .email(faker.internet().emailAddress())
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .dateUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        user = userRepository.save(user);

        User foundUser = userRepository.findByUsername(user.getUsername()).<CustomException>orElseThrow(() -> {
            throw new CustomException("User info cannot be found", HttpStatus.NOT_FOUND);
        });

        assertThat(user.getUsername()).isNotNull();
        assertNotNull(user);
        assertNotNull(foundUser);
        assertThat(foundUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(foundUser).isExactlyInstanceOf(User.class);
    }


    @Test
    @DisplayName("Fetch User - Repository Test")
    void testFetchUserByEmail() {
        user = User.builder()
                .id(1L)
                .username("timadeshola")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .empId(UUID.randomUUID().toString())
                .status(Status.ACTIVE.getStatus())
                .email(faker.internet().emailAddress())
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .dateUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        user = userRepository.save(user);

        User foundUser = userRepository.findByEmail(user.getEmail()).<CustomException>orElseThrow(() -> {
            throw new CustomException("User info cannot be found", HttpStatus.NOT_FOUND);
        });

        assertThat(user.getEmail()).isNotNull();
        assertNotNull(user);
        assertNotNull(foundUser);
        assertThat(foundUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(foundUser).isExactlyInstanceOf(User.class);
    }


}