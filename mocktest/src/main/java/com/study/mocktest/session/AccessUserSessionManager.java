package com.study.mocktest.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessUserSessionManager {
    private static final String USER_SESSION_KEY = "ACCESS_USER";
    private static final Long SESSION_TIME = 100000L;

    public void saveUser(HttpServletRequest servletRequest, AccessUser accessUser) {
        servletRequest.getSession().setAttribute(USER_SESSION_KEY, accessUser);
    }

}
