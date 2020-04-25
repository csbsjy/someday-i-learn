## 데이터바인딩 추상화: Converter와 Formatter

서로다른 타입 간에도 가능해야한다.

**Converter Interface**

```java
public class EventConverter {
    public static class StringToEventConverter implements Converter<String, Event>{

        @Override
        public Event convert(String source) {
            return new Event(Integer.parseInt(source));
        }
    }

    public static class EventToStringConverter implements Converter<Event, String>{
        @Override
        public String convert(Event event) {
            return event.getId().toString();
        }
    }
}
```

-  상태정보가 없다 ----> 빈으로 등록해도됨

- ConverterRegistry 에 등록해야 함

```java
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new EventConverter.StringToEventConverter());
        registry.addConverter(new EventConverter.EventToStringConverter());
    }
}
```

integer 같은것은 기본적으로 등록된 Converter가 있음


**Formatter Interface**

```java
public class EventFormatter implements Formatter<Event> {

    @Override
    public Event parse(String source, Locale locale) throws ParseException { // 기본적으로 Local 정보를 가져옴
        return new Event(Integer.parseInt(source));
    }

    @Override
    public String print(Event event, Locale locale) {
        return event.getId().toString();
    }
}
```

```java
   @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new EventConverter.StringToEventConverter());
        registry.addConverter(new EventConverter.EventToStringConverter());

        registry.addFormatter(new EventFormatter());
    }
```

+ MessageSource 와 연계해서 ? 


ConversionService에 등록된다. 
- 스프링 MVC, 빈설정  SpEL에서 사용
- DefaultFormattingConversionService
    - FormatterRegistry
    - ConversionService
    
```java
public interface FormatterRegistry extends ConverterRegistry {
```

스프링부트
 - WebConversionService를 빈으로 등록 ---> DefaultConversionService 상속
 - Formatter, Converter 빈을 자동으로 등록해준다. Config 에 addFormatter 안해도 됨! 
 
 @WebMvcTest 
 웹과 관련된 Bean 만 등록, 주로 Controller
 테스트에 필요한 클래스를 추가해주어야 함.