package com.example.unittesting.service;

import com.example.unittesting.model.response.TodoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 11:31 PM
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomRestTempTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String url;

    @BeforeEach
    public void setUp() {
        url = "https://jsonplaceholder.typicode.com/todos/1";
    }

    @Test
    @DisplayName("Todo API Test")
    void testToDoApiUsingTestRestTemplate() {
        TodoResponse response = this.restTemplate.getForObject(url, TodoResponse.class);
        assertThat(response.getTitle()).isEqualTo("delectus aut autem");
        assertThat(response.getUserId()).isEqualTo(1);
    }
}