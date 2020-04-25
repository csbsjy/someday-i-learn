## 15. 데이터 바인딩 추상화: PropertyEditor

프로퍼티 값을 타겟 객체에 설정하는 기능
사용자가 입력한 값을 도메인 모델에 동적으로 변환


문자열 ----> Property Type 으로 변환

DataBinder interface


**PropertyEditor**
가장 고전적인 방식


```java
    // TODO: event 에 들어오는 Integer를 Event로 받는다
    @GetMapping("/event/{event}")
    public String getEvent(@PathVariable Event event){
        System.out.println(event);
        return String.valueOf(event.getId());
    }
```

TODO: event로 들어오는 Integer를 Event로 바인딩하자

```java
public class EventEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Event event = (Event) getValue();
        return event.getId().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Event(Integer.parseInt(text)));
    }
}

```

**매우 주의해야하는 점!!**
공유하는 Value 는 PropertyEditor 가 가지고 있는 값이다.
- Stateful
- 서로다른 스레드에 공유
- PropertyEditor 의 구현체는 스레드에 공유하면 한된다
- 빈으로 등록해서 쓰면 안된다
- ThreadScope 빈으로 만들면 OK. 그래도 안하는걸 권장해요


추가로 컨트롤러에서 바인더를 설정한다
```java
    @InitBinder
    public void init(WebDataBinder binder){
        binder.registerCustomEditor(Event.class, new EventEditor());
    }
```



결론) 구현도 편리하지 않고 스레드세이프하지도 않음. 
- Object <-----> String 변화만 가능함