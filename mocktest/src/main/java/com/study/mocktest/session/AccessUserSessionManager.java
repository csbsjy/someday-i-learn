package com.study.mocktest.session;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class AccessUserSessionManager {
    private static final String USER_SESSION_KEY = "ACCESS_USER";

    private final HttpServletRequest servletRequest;
    private final HttpSession httpSession;

    public void saveUser(AccessUser accessUser) {
        servletRequest.getSession().setAttribute(USER_SESSION_KEY, accessUser);
    }

    public AccessUser extractUser() {
        return (AccessUser) httpSession.getAttribute(USER_SESSION_KEY);
    }

}
