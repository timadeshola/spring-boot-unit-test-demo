package com.example.unittesting.model.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 11:35 PM
 */
@SpringBootTest
@Profile("test")
class TodoResponseTest {

    private TodoResponse response;

    @BeforeEach
    public void setUp() {
        response = TodoResponse.builder().build();
    }

    @Test
    @DisplayName("Todo Response Test")
    void testTodoResponse() {
        response = TodoResponse.builder()
                .userId(1)
                .id(1)
                .title("This is todo app")
                .completed(true)
                .build();

        assertThat(response).isNotNull();
        assertThat(response.getUserId()).isEqualTo(1);
    }

}