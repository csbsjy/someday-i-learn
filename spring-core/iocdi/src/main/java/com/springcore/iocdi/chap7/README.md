## MessageSource

국제화 기능을 제공하는 인터페이스

ApplicationContext extends MessageSource


스프링부트를 사용하면 messages.properties, messages_ko_kr.properties 를 사용하면 알아서 읽어줌.

```java
messageSource.getMessage("greeting", new String[]{"jaeyeon"}, Locale.KOREA);
```

원래는 ResourceBundleMessageSource 를 빈으로 등록해야하는데 부트를 사용하면 ㅇㅋㅇㅋ
 
자동으로 제공되는 빈을 릴로더블하게 할 수 있다.
 
```java
@Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
```


```java
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3); // 리소스를 캐시하는 시간을 지정할 수 있다. 
        return messageSource;
    }
```

릴로더블가능한 메시지소스이기 때문에 message 를 러닝 중 바꾸고 빌드(필수)하면 바로 새로운 값을 읽어온다. 신기신기. 

