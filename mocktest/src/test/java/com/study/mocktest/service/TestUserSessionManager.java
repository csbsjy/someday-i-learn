package com.study.mocktest.service;

import com.study.mocktest.session.AccessUser;
import com.study.mocktest.session.UserSessionManager;
import org.springframework.context.annotation.Profile;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Profile("test")
@Component
public class TestUserSessionManager implements UserSessionManager {
    private static final String USER_SESSION_KEY = "ACCESS_USER";

    private final MockHttpServletRequest servletRequest;

    public TestUserSessionManager() {
        MockHttpSession httpSession = new MockHttpSession();
        httpSession.setAttribute(USER_SESSION_KEY, new AccessUser("a1010100z"));
        servletRequest = new MockHttpServletRequest();
        servletRequest.setSession(httpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
    }

    public void saveUser(AccessUser accessUser) {
        servletRequest.getSession().setAttribute(USER_SESSION_KEY, accessUser);
    }

    public AccessUser extractUser() {
        return (AccessUser) servletRequest.getSession().getAttribute(USER_SESSION_KEY);
    }
}
