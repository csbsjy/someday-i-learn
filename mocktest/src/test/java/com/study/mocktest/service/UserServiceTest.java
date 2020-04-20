package com.study.mocktest.service;

import com.study.mocktest.domain.User;
import com.study.mocktest.domain.UserRepository;
import com.study.mocktest.dto.UserLoginRequestDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserServiceTest.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    MockHttpServletRequest servletRequest;

    @Mock(name = "httpSession")
    MockHttpSession mockHttpSession;

    @InjectMocks
    UserService userService;

    @BeforeAll
    void setUp() {
        mockHttpSession = new MockHttpSession();
        servletRequest.setSession(mockHttpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
    }

    @Test
    void 로그인성공시_세션에유저정보_저장() {
        //given
        mockHttpSession = new MockHttpSession();
        servletRequest.setSession(mockHttpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
        when(userRepository.findByUserId("a1010100z")).thenReturn(new User("a1010100z", "1234"));

        userService.login(new UserLoginRequestDto("a1010100z", "1234"));
//        mockHttpSession.setAttribute("USER", "1234");
        System.out.println(mockHttpSession.getAttribute("USER"));
    }

}
