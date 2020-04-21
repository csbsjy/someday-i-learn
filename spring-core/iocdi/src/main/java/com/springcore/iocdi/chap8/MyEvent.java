package com.springcore.iocdi.chap8;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent { // 4.2 이전
    private int data;

    public MyEvent(Object source) { // 이벤트를 발생시킨 소스
        super(source);
    }

    public MyEvent(Object source, int data) {
        super(source);
        this.data =data;
    }
}
