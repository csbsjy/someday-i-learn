package com.study.web;

import com.study.dto.UserLoginDto;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginDto> login(@RequestBody UserLoginDto userLoginDto) {
        UserLoginDto loginUser = userService.login(userLoginDto);
        return new ResponseEntity(loginUser, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<UserLoginDto> loginGet(@RequestBody UserLoginDto userLoginDto) {
        log.info(userLoginDto.toString());
        UserLoginDto loginUser = userService.login(userLoginDto);
        return new ResponseEntity(loginUser, HttpStatus.OK);
    }

}
