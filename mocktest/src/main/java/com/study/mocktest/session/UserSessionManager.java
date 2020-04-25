package com.study.mocktest.session;

public interface UserSessionManager {
    void saveUser(AccessUser accessUser);
    AccessUser extractUser();
}
