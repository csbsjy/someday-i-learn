package com.study.mocktest.dto;


public class UserLoginRequestDto {
    private String userId;
    private String password;

    public UserLoginRequestDto(String id, String password) {
        this.userId = id;
        this.password = password;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getPassword() {
        return this.password;
    }
}
