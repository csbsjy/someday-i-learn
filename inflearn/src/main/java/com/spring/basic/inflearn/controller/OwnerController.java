package com.spring.basic.inflearn.controller;

import com.spring.basic.inflearn.aspect.LogExecutionTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerController {

    @LogExecutionTime
    @GetMapping("/owner")
    public String owner() {
        return "Hello, Aop";
    }

}
