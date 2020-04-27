package com.core.aop.chap19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary // 같은 타입의 빈이 두개이상있을 때 먼저주입받는다
//@Component
public class ProxySimpleEventService implements EventService{

    @Autowired SimpleEventService eventService; // Proxy는 RealSubject Type 빈을 주입받아야함.

    @Override
    public void createEvent() throws InterruptedException {
        long begin = System.currentTimeMillis(); // 여기에도 중복코드가 생김!

        eventService.createEvent(); // 귀찮다

        System.out.println(System.currentTimeMillis() - begin);
    }

    @Override
    public void publishEvent() throws InterruptedException {
        long begin = System.currentTimeMillis();

        eventService.publishEvent();

        System.out.println(System.currentTimeMillis() - begin);
    }

    @Override
    public void deleteEvent() {
        eventService.deleteEvent();
    }
}
