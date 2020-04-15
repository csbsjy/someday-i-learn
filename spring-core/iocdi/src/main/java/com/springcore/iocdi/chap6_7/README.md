## IoC 컨테이너 6부: Environment 



ApplicationContext는 BeanFactory + EnvironmentCapable 기능도 있음

Profile 은 빈들의 묶음이다. "환경"이다. 

```java
Environment environment = ApplicationContext.getEnvironment();
```

아무 설정을 하지 않으면 defaultProfile


@Configuration @Profile("test")
@Component @Configuration @Profile("test")

@Configuration @Profile("!prod") ---------> prod 가 아닌 경우에 적용! 신기해! 


- 프로파일 표현식
    - ! : not
    - & : and
    - | : or
    
 

## IoC 컨테이너 7부: Property 

프로퍼티는 우선순위가 있다.

environment.getProperty() 


.properties 파일로 관리도 가능

```java
@PropertySource("classpath:/app.properties") 
```

로 프로퍼티 파일의 위치를 알려줘야 함. 