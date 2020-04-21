package com.springcore.iocdi.chap8;

// ApplicationEvent 를 상속받지 않아도 됨.
// 깔끔한 POJO. 피침투성.
// 스프링이 내 코드애 노출되지 않는다. "스프링의 철학"
// 테스트가 편하고 유지보수하기 쉬워진다.
public class NewEvent {
    private int data;
    private Object source;

    public NewEvent(int data, Object source) {
        this.data = data;
        this.source = source;
    }
}
