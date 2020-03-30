package com.study.domain;

import java.util.Arrays;

public enum UserType {
    ADMIN("관리자"), GENERAL("일반회원");

    private String name;

    UserType(String name) {
        this.name = name;
    }

    public static boolean isAdmin(String name) {
        return ADMIN.equals(findByName(name));
    }

    public static boolean isGeneral(String type) {
        return GENERAL.equals(findByName(type));
    }

    private static UserType findByName(String name) {
        return Arrays.stream(UserType.values())
                .filter(inputName -> name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s 에 해당하는 유저는 없습니다", name)));
    }
}
