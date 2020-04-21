package com.resource_validation.resource_validation.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    Validator validator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Event event = new Event();
        event.setTitle("");

        EventValidator validator = new EventValidator();

        Errors errors = new BeanPropertyBindingResult(event, "event"); // Spring MVC 에서 구현체는 자동으로 생성함

        validator.validate(event, errors);

        System.out.println(errors.hasErrors());
        errors.getAllErrors().forEach(error -> {
                    Arrays.stream(error.getCodes()).forEach(System.out::println);
                    System.out.println(error.getDefaultMessage()
                    );
                }
        );
    }
}
