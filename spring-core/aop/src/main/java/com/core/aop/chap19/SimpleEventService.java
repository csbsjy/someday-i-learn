package com.core.aop.chap19;

import com.core.aop.chap20.PerfLogging;
import org.springframework.stereotype.Service;

// RealSubject
@Service
public class SimpleEventService implements EventService{

    @Override
    @PerfLogging
    public void createEvent() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("create an event");
    }

    @Override
    @PerfLogging
    public void publishEvent() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("publish an event");
    }

    @Override
    public void deleteEvent(){
        System.out.println("delete an event");
    }
}
