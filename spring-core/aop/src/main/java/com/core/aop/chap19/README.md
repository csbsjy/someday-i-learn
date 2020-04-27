## 19. 스프링 AOP: 프록시기반 AOP

### 프록시패턴

Client는 Interface 타입으로 Proxy 객체를 사용한다.
Proxy 객체는 RealSubject를 감싸고있다.

왜? 
- 접근제어, 부가기능


```java
package com.core.aop.chap19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary // 같은 타입의 빈이 두개이상있을 때 먼저주입받는다
@Component
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

```

귀찮다. 비용이다. 또 중복이 발생한다.


그래서 스프링 AOP가 등장했다.

1. 동적프록시 ---> 런타임에 만든다. 
2. 스프링 IoC는 이미 동적프록시 빈을 만들어 등록시킨다. 
ex) AbstractAutoProxyCreator implements BeanProcessor
- 빈을 만들고 감싼 프록시 빈을 만든다. 
 
 
 