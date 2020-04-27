package com.core.aop.chap19;

public interface EventService {
    void createEvent() throws InterruptedException;
    void publishEvent() throws InterruptedException;
    void deleteEvent();
}
