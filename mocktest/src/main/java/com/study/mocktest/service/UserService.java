package com.study.mocktest.service;

import com.study.mocktest.domain.User;
import com.study.mocktest.domain.UserRepository;
import com.study.mocktest.dto.UserLoginRequestDto;
import com.study.mocktest.session.AccessUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AccessUser login(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(IllegalAccessError::new);

        if (!user.checkPassword(userLoginRequestDto.getPassword())) {
            throw new IllegalArgumentException("패스워드가 틀렸습니다!");
        }

        return AccessUser.of(userLoginRequestDto);
    }
}
