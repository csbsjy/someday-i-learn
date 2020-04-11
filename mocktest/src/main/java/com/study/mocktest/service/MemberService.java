package com.study.mocktest.service;

import com.study.mocktest.domain.MemberRepository;
import com.study.mocktest.domain.User;
import com.study.mocktest.dto.UserLoginRequestDto;
import com.study.mocktest.session.AccessUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public AccessUser login(UserLoginRequestDto userLoginRequestDto) {
        User user = memberRepository.findByUserId(userLoginRequestDto.getUserId());

        if (!user.checkPassword(userLoginRequestDto.getPassword())) {
            throw new IllegalArgumentException("패스워드가 틀렸습니다!");
        }

        return AccessUser.of(userLoginRequestDto);
    }
}
