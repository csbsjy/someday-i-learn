## 스프링 AOP: @AOP

```java
@Aspect
@Component
public class PerfAspect {

    @Around("execution(* com.core.aop..*.EventService.*(..))") // Advice를 어떻게 제공할 것인지?
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable { // Advice 제공대상
        long begin = System.currentTimeMillis();
        Object proceed = pjp.proceed(); // Method 실행
        System.out.println(System.currentTimeMillis() - begin);
        return proceed;
    }


}
```

```java
    @Around("@annotation(PerfLogging)") // Advice를 어떻게 제공할 것인지?
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable { // Advice 제공대상
        long begin = System.currentTimeMillis();
        Object proceed = pjp.proceed(); // Method 실행
        System.out.println(System.currentTimeMillis() - begin);
        return proceed;
    }
```


@Before
@AfterReturning
@AfterThrowing
@Around