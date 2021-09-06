package com.example.unittesting.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 8:39 PM
 */
@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class DatabaseConfig {

    private final Environment env;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")))
                .url(env.getProperty("spring.datasource.url"))
                .username(env.getProperty("spring.datasource.username"))
                .password(env.getProperty("spring.datasource.password"))
                .build();
    }

}