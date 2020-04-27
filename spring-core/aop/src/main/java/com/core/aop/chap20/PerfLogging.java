package com.core.aop.chap20;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.CLASS) // 바이트코드로 남아있음, 런타임까진 불필요
public @interface PerfLogging {
}
