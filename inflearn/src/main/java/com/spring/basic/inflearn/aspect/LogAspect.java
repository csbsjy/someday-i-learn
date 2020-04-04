package com.spring.basic.inflearn.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component // Bean 만 Aspect가 될 수 있음
@Aspect
@Slf4j
public class LogAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object value = joinPoint.proceed();

        stopWatch.stop();

        log.info(stopWatch.prettyPrint());

        return value;
    }
}
