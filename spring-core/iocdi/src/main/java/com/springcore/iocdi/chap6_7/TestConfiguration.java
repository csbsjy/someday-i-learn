package com.springcore.iocdi.chap6_7;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test") // test profile 일 때만 설정이 되는 Configuration
public class TestConfiguration {

}
