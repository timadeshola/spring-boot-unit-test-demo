package com.example.unittesting;

import com.example.unittesting.core.exceptions.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Profile("test")
class UnitTestingApplicationTests {

    @Test
    void contextLoads() {
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Not supported");
        });
        assertEquals("Not supported", exception.getMessage());
    }

    @Test
    void testCustomException() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            throw new CustomException("Error in the app");
        });
        assertEquals("Error in the app", exception.getMessage());
    }

    @Test
    void testCustomExceptionWithStatus() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            throw new CustomException("Error in the app", HttpStatus.NO_CONTENT);
        });

        assertEquals("Error in the app", exception.getMessage());
        assertEquals(HttpStatus.NO_CONTENT, exception.getStatus());
    }

}
