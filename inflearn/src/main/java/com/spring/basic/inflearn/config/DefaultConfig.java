package com.spring.basic.inflearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfig {

    @Bean
    public String hello() {
        return "Hello";
    }

}
