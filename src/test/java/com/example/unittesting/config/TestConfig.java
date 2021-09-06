package com.example.unittesting.config;

import com.example.unittesting.persistence.repository.UserRepository;
import com.example.unittesting.service.UserService;
import com.example.unittesting.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 9:16 PM
 */
@TestConfiguration
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository);
    }
}