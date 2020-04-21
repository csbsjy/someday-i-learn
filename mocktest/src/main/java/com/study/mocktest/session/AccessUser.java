package com.study.mocktest.session;

import com.study.mocktest.dto.UserLoginRequestDto;
import lombok.Getter;

@Getter
public class AccessUser {

    private final String userId;

    public AccessUser(String userId) {
        this.userId = userId;
    }

    public static AccessUser of(UserLoginRequestDto userLoginRequestDto) {
        return new AccessUser(userLoginRequestDto.getEmail());
    }
}
