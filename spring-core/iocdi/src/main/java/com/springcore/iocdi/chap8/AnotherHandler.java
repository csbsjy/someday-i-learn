package com.springcore.iocdi.chap8;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AnotherHandler {

    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE) // 순서를 조정할 수 있다.
    public void handle(NewEvent newEvent){
        // 두 핸들러 모두 호출되는데 순차적임
        // 동시에 멀티스레드로 실행하는 것이 아님.
        // 하나의 스레드가 순서로.
    }
}
