package com.example.unittesting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
class UnitTestingApplicationTests {

    @Test
    void contextLoads() {
    }

}
