package com.study.mocktest.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AccessUserSessionManager.class)
class AccessUserSessionManagerTest {

    MockHttpServletRequest servletRequest;

    AccessUserSessionManager userSessionManager;

    @BeforeEach
    void setUp() {
        MockHttpSession httpSession = new MockHttpSession();
        servletRequest = new MockHttpServletRequest();
        servletRequest.setSession(httpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
        userSessionManager = new AccessUserSessionManager(servletRequest);
    }

    @DisplayName("유저를 세션에 저장한다")
    @Test
    void sessionSave() {
        userSessionManager.saveUser(new AccessUser("a1010100z"));
        assertThat(((AccessUser) servletRequest.getSession().getAttribute("ACCESS_USER")).getUserId())
                .isEqualTo("a1010100z");
    }

}
