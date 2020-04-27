## 18. 스프링 AOP: 개념소개

aspectj 와 연동해서 사용할 수 있는 기능제공
스프링 자체에서 구현한 spring-aop 활용가능

spring-transaction, spring-cache 등이 aop를 활용하고 있음


### AOP란?
흩어진 Aspect를 모듈화할 수 있는 프로그래밍 기법
OOP와 보완관계라고 할 수 있음.


ex) 트랜잭션
A,B,C가 모두 트랜잭션 처리가 필요하다?
아래 코드를 다 심는다
```sql
set autocommit = false
commit or rollback
```

흩어진 비슷한 Concern을 한군데로 모음으로써 관리의 용이성.


### AOP 용어

1. Aspect: 모듈, 묶은 것, Target: 적용대상
2. Advice: 해야할 일
3. Pointcut: 어디에 적용해야하는지 --- 구체적인 Join point
4. Join point: 끼어들 수 있는 지점. 가장 흔하게 사용되는 것은 "메서드 실행시점". 스펙에 가까움. 어디어디 끼어들 수 있다. 


### AOP 적용방법

1. Compile: 이미 조작이 된 바이트코드를 추가. 로드타임,런타임 부하가 없음 / 별도의 컴파일링을 한번 더해야함
2. Loading: A는 그 자체로 순수하게 컴파일. A를 로딩하는 시점에 바이트코드를 변경.(Load time Weaving) 로딩시점 부하, 로드타임 위버로 인한 약간의 성능부하/다양한문법 사용가능
3. Runtime: 빈을 만드는 초기의 성능저하 / 실제 애플리케이션 요청 떄는 성능부하가 아님. 별도의 설정 불필요. 문법이 쉬움. AspectJ 연동가
: Spring AOP, A Bean에 Aspect를 적용하는 것을 스프링이 알고있다. A Bean을 만들 때(런타임) A라는 타입의 프록시 빈을 만든다.
  그 프록시 빈이 A의 메서드를 호출하기 전에 Aspect를 실행하고 A를 실행한다. 
  



### 구현체
1. AspectJ
2. Spring AOP ------ AspectJ에 비해 매우 국한적임