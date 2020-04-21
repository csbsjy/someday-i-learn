package com.springcore.iocdi.chap8;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// 4.2 version 이전
@Component
public class MyEventHandler implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println(myEvent.getSource());
    }
}
