package com.core.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication aop = new SpringApplication(AopApplication.class);
        aop.setWebApplicationType(WebApplicationType.NONE);
        aop.run(args);
    }

}
