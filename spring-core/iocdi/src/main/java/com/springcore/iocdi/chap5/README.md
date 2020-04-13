

## Bean 의 Scope

기본은 Singleton. 아마도 거의 Singleton 을 사용할 것이다. 


### 언제 프로토타입? (매번 새로운 인스턴스)
```java
@Component
@Scope("prototype")
public class Proto {
}
```


프로토타입 빈이 싱글톤 빈을 참조하면? - 괜찮음. 의도한대로
싱글톤빈이 프로토타입 빈을 사용하면? - 프로토타입 빈이 의도한대로 사용되지 않음. 프로토타입빈도 싱글톤처럼 사용됨. 

```java
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) // dynamic Proxy using cglib 
public class Proto {
}
```

PROTO를 클래스기반의 프록시로 감싸라. 그리고 그 프록시를 사용하게 해라.
Single이 Proto를 직접참조하면 안됨. 새로운 인스턴스로 바꿔줄 "Proxy"가 필요함. 
기존 자바는 인터페이스의 프록시만 만들 수 있는데 cglib을 사용하여 클래스기반의 프록시를 만들 수 있음.
이 프록시도 타입은 Proxy이기 때문에 @Autowired가 가능한 것.


또는, 사용하는 부분에
```java
@Autowired
private ObjectProvider<Proto> protos;

public Proto getProto() {
    return protos.getIfAvailable();
}
```



### 싱글톤 객체를 사용할 때 중요한 점
프로퍼티가 공유가되기 때문에 스레드세이프해야함.
ApplicationContext 초기구동 시 인스턴스 생성.
