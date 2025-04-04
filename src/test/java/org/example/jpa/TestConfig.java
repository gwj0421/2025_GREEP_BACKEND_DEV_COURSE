package org.example.jpa;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {
    @Bean
    @Primary
    public QueryCountInspector queryCountInspector() {
        return new QueryCountInspector();
    }
}
