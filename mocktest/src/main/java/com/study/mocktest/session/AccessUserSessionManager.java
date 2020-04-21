package com.study.mocktest.session;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class AccessUserSessionManager implements UserSessionManager {
    private static final String USER_SESSION_KEY = "ACCESS_USER";

    private final HttpServletRequest servletRequest;

    public void saveUser(AccessUser accessUser) {
        servletRequest.getSession().setAttribute(USER_SESSION_KEY, accessUser);
    }

    public AccessUser extractUser() {
        return (AccessUser) servletRequest.getSession().getAttribute(USER_SESSION_KEY);
    }

}
