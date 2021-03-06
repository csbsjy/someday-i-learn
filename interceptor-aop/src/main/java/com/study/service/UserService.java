package com.study.service;

import com.study.dto.UserLoginDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    public UserLoginDto login(UserLoginDto userLoginDto) {
        return userLoginDto;
    }

}
