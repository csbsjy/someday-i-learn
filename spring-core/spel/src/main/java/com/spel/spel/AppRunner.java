package com.spel.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Value("#{1+1}") // 표현식 사용가능
    int value;

    @Value("#{'hello' + 'world'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean trueOrFalse;

    @Value("${my.data}") // 프로퍼티를 참고하는 방법
    String property;

    @Value("#{${my.data} eq 100}") // 프로퍼티를 참고하는 방법
    String propertyInExpression;

    @Value("#{sample.data}")
    String data;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
