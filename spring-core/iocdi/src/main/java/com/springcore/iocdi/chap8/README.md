## ApplicationEventPublisher

이벤트프로그래밍에 필요한 인터페이스 제공 - 옵저버패턴 구현체


스프링버전 4.2 이전
```java

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
```
```java
@Component
public class MyEventHandler implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println(myEvent.getSource());
    }
}
```


4.2 이후부터는, 스프링의 철학이 반영되었다.
```java
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
```

```java
@Component // Handler 는 빈이어야 한다.
public class NewEventHandler {

    @EventListener
    public void handle(NewEvent newEvent){
        System.out.println("이벤트 들어왔다!");
    }
}
```

만약 이벤트핸들러가 여러개라면? 

기본적으로 여러개 모두 동작하지만 하나의 스레드에서 "순차적으로 동작한다."
```java
@EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE) // 순서를 조정할 수 있다.
    public void handle(NewEvent newEvent){
        // 두 핸들러 모두 호출되는데 순차적임
        // 동시에 멀티스레드로 실행하는 것이 아님.
        // 하나의 스레드가 순서로.
    }
```

비동기적으로 하고 싶다면, @Async , @EnableAsync + Thread 관련 설정이 필요하다.
```java
    @EventListener
    @Async  
    public void handle(NewEvent newEvent){
    }
```


스프링에서 제공하는 기본 이벤트
- ContextRefreshedEvent 
- ContextStartedEvent
- ContextStoppedEvent
- ContestClosedEvent
- RequestHandledEvent