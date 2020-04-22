package com.study.mocktest.api;

import com.study.mocktest.dto.UserLoginRequestDto;
import com.study.mocktest.service.UserService;
import com.study.mocktest.session.AccessUser;
import com.study.mocktest.session.UserSessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final UserSessionManager userSessionManager;
    private final HttpServletRequest servletRequest;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        System.out.println(servletRequest.getClass());
        AccessUser accessUser = userService.login(userLoginRequestDto);
        userSessionManager.saveUser(accessUser);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
