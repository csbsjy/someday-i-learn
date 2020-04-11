package com.study.web.interceptor;

import com.study.domain.UserType;
import com.study.dto.UserLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private static final String REQUEST_BODY = "requestBody";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("GET".equals(request.getMethod())) {
            return false;
        }
        // TODO: 이게 안된다 ! Stream 을 DTO 로 매핑해야함. 근데, 이 정보는 1회성임 ..
//        UserLoginDto loginUser = extractUserLoginDto(request);
//        if(UserType.isAdmin(loginUser.getType())){
//            log.info("관리자 {} login 시도합니다 ------ {}", loginUser.getUserId(), LocalDateTime.now());
//        }
//        if(UserType.isGeneral(loginUser.getType())){
//            log.info("회원 {} login 시도합니다 ------ {}", loginUser.getUserId(), LocalDateTime.now());
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserLoginDto loginUser = extractUserLoginDto(request);
        if (UserType.isAdmin(loginUser.getType())) {
            log.info("관리자 {} login  ------ {}", loginUser.getUserId(), response.getStatus());
        }
        if (UserType.isGeneral(loginUser.getType())) {
            log.info("회원 {} login  ------ {}", loginUser.getUserId(), response.getStatus());
        }
    }

    private UserLoginDto extractUserLoginDto(HttpServletRequest request) {
        return (UserLoginDto) request.getAttribute(REQUEST_BODY);
    }
}
