## Validation 추상화
org.springframework.validation.Validator

특징
- 모든 계층에서 사용가능함
- Bean Validation 애노테이션을 사용할 수 있음

```java
public class EventValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Event.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "notempty", "Empty title is not allowed");
    }
}
```

스프링부트를 사용하면 LocalValidatorFactoryBean 을 자동으로 빈으로 등록한다. 
