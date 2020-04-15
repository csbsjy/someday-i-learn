package com.study.mocktest.api;

import com.study.mocktest.dto.UserLoginRequestDto;
import com.study.mocktest.service.MemberService;
import com.study.mocktest.session.AccessUser;
import com.study.mocktest.session.AccessUserSessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebController {

    private final MemberService memberService;
    private final AccessUserSessionManager userSessionManager;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        AccessUser accessUser = memberService.login(userLoginRequestDto);
        userSessionManager.saveUser(accessUser);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
