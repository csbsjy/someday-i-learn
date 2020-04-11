package com.study.mocktest.session;

import com.study.mocktest.dto.UserLoginRequestDto;

public class AccessUser {

    private final String userId;
    private final String password;

    public AccessUser(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public static AccessUser of(UserLoginRequestDto userLoginRequestDto) {
        return new AccessUser(userLoginRequestDto.getUserId(), userLoginRequestDto.getPassword());
    }
}
