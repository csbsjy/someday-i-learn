package com.core.aop.chap20;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerfAspect {

    @Around("@annotation(PerfLogging)") // Advice를 어떻게 제공할 것인지?
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable { // Advice 제공대상
        long begin = System.currentTimeMillis();
        Object proceed = pjp.proceed(); // Method 실행
        System.out.println(System.currentTimeMillis() - begin);
        return proceed;
    }


}
