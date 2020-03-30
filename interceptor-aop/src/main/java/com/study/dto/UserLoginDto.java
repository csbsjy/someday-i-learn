package com.study.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginDto {
    private String userId;
    private String password;
    private String type;

    public UserLoginDto(String userId, String password, String type) {
        this.userId = userId;
        this.password = password;
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
