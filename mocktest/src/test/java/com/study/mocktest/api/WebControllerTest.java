package com.study.mocktest.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WebControllerTest {

    @Autowired
    HttpSession httpSession;

    @Test
    void httpSession_테스트() {
        assertNotNull(httpSession);
        System.out.println(httpSession.getClass());
    }
}