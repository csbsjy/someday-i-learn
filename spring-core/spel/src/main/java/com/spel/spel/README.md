## 17. SpEL(스프링 Expression Language)

객체그래프 조회 및 조작하는 기능

이유) EL 과 비슷, 메서드호출 지원, 문자열 템플릿 기능 지원



```java
    @Value("#{1+1}") // 표현식 사용가능
    int value;

    @Value("#{'hello' + 'world'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean trueOrFalse;
```

표현식을 실행한 다음에 프로퍼티에 넣어준다

```java
    @Value("${my.data}") // 프로퍼티를 참고하는 방법
    String property;
```

```java
    @Value("#{${my.data} eq 100}") // 프로퍼티를 참고하는 방법
    String propertyInExpression;
```
표현식 안에 property를 넣는 것도 가능함

```java
    @Value("#{sample.data}")
    String data;
```
빈을 참고하는 것도 가능함. 메서드호출도 가능함.


Map, List, 등 되게 다양한 표현식을 지원함. 필요하면 그때 공식문서 찾아보자.





### 그래서 어디서쓰냐?
@Value
@ConditionalOnExpression // 선택적으로 빈을 선택할 때. EventListener 등록할 때 해봤어!
@SpringSecurity   --> 각종 애노테이션,, 안의 메서드,,값,,, 
@Query



ExpressionParser 기반이다. 
```java
ExpressionParser parser = new SpelExpressionParser();
Expression expression = parser.parseExpression("2+100");
Integer value = expression.getValue(Integer.class);        
```