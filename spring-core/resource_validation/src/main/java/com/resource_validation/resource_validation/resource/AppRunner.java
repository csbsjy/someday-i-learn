package com.resource_validation.resource_validation.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

public class AppRunner implements ApplicationRunner {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Default
        System.out.println(applicationContext.getClass()); // WebServerApplicationContext

        Resource resource = applicationContext.getResource("classpath:test.xml"); // 접두어에의해 ClassPathResource 가 나왔다.
        System.out.println(resource.getClass());

        Resource resource2 = applicationContext.getResource("test.xml"); // 접두어에의해 ClassPathResource 가 나왔다.
        System.out.println(resource.getClass()); // ServletContextResource. 내장톰캣은 기본적으로 context path 가 없음.

    }
}
