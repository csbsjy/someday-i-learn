package com.study.mocktest.domain;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    public User findByUserId(String userId) {
        return new User();
    }
}
