## Resource 추상화
org.springframework.core.io.Resource

1. java.net.URL 을 추상화
- classpath 기준으로 리소스를 가져오는 기능이 없었음
- 여러 prefix 를 지원할 수 있도록 추상화함. 
- ServletContext 를 기준으로 상대경로로 읽어오는 기능은 다 Resource 로 추상화되어 나오는 것 

```java
UrlResource
ClassPathResource // classpath: 접두어 --> 리소스 강제
FileSystemResource // file:// 접두어 
ServletContextResource
```

리소스 타입은 ApplicationContext 타입에 따라 결정됨. 
(ClassPathXmlApplicationContext 등등)

보통 접두어 없으면 ApplicationContext 타입에따라 자동으로 접두어를 붙여주지만
보통 ApplicationContext 는 ServletContextResource 를 사용하는 경우가 많음.
따라서, 접두어를 통해 어떤 Context 알도록 하는 것이 좋음. 

```java
        //Default
        System.out.println(applicationContext.getClass()); // WebServerApplicationContext

        Resource resource = applicationContext.getResource("classpath:test.xml"); // 접두어에의해 ClassPathResource 가 나왔다.
        System.out.println(resource.getClass());

        Resource resource2 = applicationContext.getResource("test.xml"); // 접두어에의해 ClassPathResource 가 나왔다.
        System.out.println(resource.getClass()); // ServletContextResource. 내장톰캣은 기본적으로 context path 가 없음. 
```