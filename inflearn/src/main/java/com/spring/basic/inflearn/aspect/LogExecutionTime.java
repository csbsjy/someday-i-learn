package com.spring.basic.inflearn.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 기본 애노테이션은 주석과도 같다. 유지 Policy를 지정해야 함.
public @interface LogExecutionTime {
}
