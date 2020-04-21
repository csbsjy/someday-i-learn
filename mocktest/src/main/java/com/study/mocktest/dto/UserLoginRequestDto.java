package com.study.mocktest.dto;


public class UserLoginRequestDto {
    private String email;
    private String password;

    public UserLoginRequestDto(String id, String password) {
        this.email = id;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
