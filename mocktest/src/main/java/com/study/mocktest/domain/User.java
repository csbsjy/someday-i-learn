package com.study.mocktest.domain;


public class User {
    private String id;
    private String password;

    public User() {
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
