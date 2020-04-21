package com.springcore.iocdi.chap8;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component // Handler 는 빈이어야 한다.
public class NewEventHandler {

    @EventListener
    public void handle(NewEvent newEvent) {
        System.out.println("이벤트 들어왔다!");
    }

    @EventListener
    public void handle(ContextRefreshedEvent event) { // 스프링에서 제공하는 기본이벤트
        System.out.println("이벤트 들어왔다!");
    }

}
