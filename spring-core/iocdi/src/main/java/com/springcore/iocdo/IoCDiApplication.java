package com.springcore.iocdo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

// SpringBoot로 넘어오고
@SpringBootApplication // 모든 ComponentScan 등의 작업을 대신 해준다. 이 자체가 하나의 Config 파일이 된 것
public class IoCDiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IoCDiApplication.class, args);
//      ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames)); // bean 이름을 볼 수 있다
//
//        BookService bookService = (BookService) context.getBean("bookService");
//
//        System.out.println(bookService.bookRepository != null);
    }

}
