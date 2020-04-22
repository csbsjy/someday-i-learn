package com.study.mocktest.service;

import com.study.mocktest.session.AccessUser;
import com.study.mocktest.session.UserSessionManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Profile("test")
@Component
public class TestUserSessionManager implements UserSessionManager {
    private static final String USER_SESSION_KEY = "ACCESS_USER";
    private Map<String, AccessUser> accessUserMap = new HashMap<>();

    public void saveUser(AccessUser accessUser) {
        accessUserMap.put(USER_SESSION_KEY, accessUser);
    }

    public AccessUser extractUser() {
        return accessUserMap.get(USER_SESSION_KEY);
    }
}
