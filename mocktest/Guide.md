## HttpSession 을 이용한 로직을 테스트하는 방법

로그인 이후의 로직을 단위테스트짜고 싶어서 찾아보다가 MockHttpSession, MockHttpServletRequest를 발견했다.
앞으로도 자주 쓰일 클래스일 것 같아서 정리를 해두려고 한다. 


MockHttpSession의 Java doc 을 보면 HttpSession 을 구현한 클래스임을 알 수 있다. 
(https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mock/web/MockHttpSession.html)
 
![image](https://user-images.githubusercontent.com/47847993/79041986-efb24900-7c2e-11ea-9f12-1cd86bd8399f.png)



일반적인 웹 서비스를 생각해보면 사용자의 로그인 정보를 세션에 저장하여 사용한다.
그리고 유저의 인증 정보가 필요할 때마다 세션에서 꺼내쓸 수 있게 된다.


게시글 작성 케이스를 생각해보면, 유저는 자신의 아이디를 그때그떄 적지 않는다.
유저는 글 제목, 글 내용만 적고 그 외 기본적인 유저의 정보는 자동으로 함께 Insert 될 것이다.


그리고 로그인 페이지를 제외한 모든 회원 전용 페이지는 세션에 유저가 없다면 접근할 수 없게 설계될 수 있다.



이런 상황에서의 테스트코드 작성은 반드시 "HttpSession"에 유저 정보가 있어야만 가능하다.
실제 HttpSession 을 사용할 수 없으니, MockHttpSession 을 사용하여 검증로직을 작성할 수 있다. 



테스트코드는 아래 코드를 기반으로 작성할 것이다.(풀코드: https://github.com/csbsjy/someday-i-learn/tree/master/mocksessiontest) 

간단히 로그인 시 세션을 저장하고 게시글 작성 시 세션에서 유저정보를 빼는 로직만 살펴보면,


**UserApiController.java**
```java
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final AccessUserSessionManager userSessionManager;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        AccessUser accessUser = userService.login(userLoginRequestDto);
        userSessionManager.saveUser(accessUser);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
``` 

login에 성공한 유저의 정보는 AccessUser 객체로 반환되고, userSessionManager에 저장된다.

**AccessUser.java**
```java
@Getter
public class AccessUser {

    private final String userId;

    public AccessUser(String userId) {
        this.userId = userId;
    }

    public static AccessUser of(UserLoginRequestDto userLoginRequestDto) {
        return new AccessUser(userLoginRequestDto.getEmail());
    }
}
```

**AccessUserSessionManager.java**
```java
@Component
@RequiredArgsConstructor
public class AccessUserSessionManager {
    private static final String USER_SESSION_KEY = "ACCESS_USER";

    private final HttpServletRequest servletRequest;

    public void saveUser(AccessUser accessUser) {
        servletRequest.getSession().setAttribute(USER_SESSION_KEY, accessUser);
    }

    public AccessUser extractUser() {
        return (AccessUser) servletRequest.getSession().getAttribute(USER_SESSION_KEY);
    }

}
```

그리고 게시글 작성 시 Controller 에서 Login 체크와 Service 에서 User 정보 획득을 할 수 있도록 하였다.
**BoardApiController.java**
```java
 @PostMapping("/board")
    public ResponseEntity<Void> write(@RequestBody ArticleUpdateRequestDto updateRequestDto) throws IllegalAccessException {
        if (sessionManager.extractUser() == null) {
            throw new IllegalAccessException("로그인하지 않은 사용자");
        }

        boardService.write(updateRequestDto);

        return ResponseEntity.ok(null);
    }
```

**BoardService.java**
```java
@Service
@RequiredArgsConstructor
public class BoardService {
    private final ArticleRepository articleRepository;
    private final AccessUserSessionManager sessionManager;

    public void write(ArticleUpdateRequestDto articleUpdateRequestDto) {
        articleRepository.save(articleUpdateRequestDto.toEntity(sessionManager.extractUser().getUserId()));
    }
}
```


이 과정에서 게시글 작성과 관련된 단위테스트를 작성할 때 문제가 발생했다.
통합테스트는 User 의 로그인세션이 없으면 아예 진행조차 할 수 없었고, boardservice 에서 게시글작성 시 게시글의 유저정보는 NULl 로 들어가기 때문이다.


세션이 있다고 치고 테스트코드를 작성할 수 없을까 하다가 MockHttpSession에 대해 알게되었다. 



BoardService 는 ArticleRepository, AccessUserSessionManager 의존을 가진다. 

```java
 private final ArticleRepository articleRepository;
 private final AccessUserSessionManager sessionManager;
```

AccessUserSessionManager 는 HttpServletRequest에 의존하고 있고, 이 HttpServletRequest와 여기서 얻어진 HttpSession 을 Mocking 하는 것이 핵심이다.

```java
    private final HttpServletRequest servletRequest; // Mocking 대상 
```

HttpServletRequest는 interface 이고, 스프링부트는 @Autowired 를 통해 프록시 빈을 자동으로 주입받는다.
테스트에서는 이 프록시 빈 대신 MockHttpServletRequest를 사용해야한다. 

다른 HttpServletRequest 를 사용할 수 있게 하기 위해 인터페이스로 분리하였다.


**UserSessionManager.java**
```java
public interface UserSessionManager {
    void saveUser(AccessUser accessUser);
    AccessUser extractUser();
}
```


그리고 BoardService 의 AccessUserManager 를 UserSessionManager 인터페이스로 바꿨다.



HttpSession 이 필요한 로직의 테스트를 위한 TestUserSessionManager 를 다음과 같이 작성할 수 있다.

```java
@Profile("test")
@Component
public class TestUserSessionManager implements UserSessionManager {
    private static final String USER_SESSION_KEY = "ACCESS_USER";

    private final MockHttpServletRequest servletRequest; // HttpServletRequest를 구현한다.

    public TestUserSessionManager() {
        MockHttpSession httpSession = new MockHttpSession();
        httpSession.setAttribute(USER_SESSION_KEY, new AccessUser("a1010100z")); // 유저가 로그인했다 치고
        servletRequest = new MockHttpServletRequest();
        servletRequest.setSession(httpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
    }

    public void saveUser(AccessUser accessUser) {
        servletRequest.getSession().setAttribute(USER_SESSION_KEY, accessUser);
    }

    public AccessUser extractUser() {
        return (AccessUser) servletRequest.getSession().getAttribute(USER_SESSION_KEY);
    }
}

``` 



이렇게 짜고 아래 테스트를 수행하면 테스트는 수행한다. 
정상적으로 세션에 저장된 유저의 아이디를 가져와서 저장하는 것이다. 

```java
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    BoardService boardService;

    @DisplayName("글 제목, 글 내용을 작성하면 유저 ID 정보까지 같이 저장된다")
    @Test
    void write() {
        //given
        ArticleUpdateRequestDto requestDto = new ArticleUpdateRequestDto("글 제목", "글 내용");

        //when
        boardService.write(requestDto);


        //then
        assertThat(articleRepository.findByUserId("a1010100z")).isNotNull();
    }
```


근데 아무리 생각해도 TestUserSessionManager 가 진짜 이상해보였다.
굳이 MockHttpServletRequest 를 사용할 이유가 하나도 없어보였다. 
UserSessionManager는 유저 정보를 저장하거나 가져오는 역할을 가지는데, 굳이 세션에 저장하고 불렁오는 것까지 "BoardService" 에서 확인을 해야할까? 싶었다.
BoardService 는 잘 감싸진 UserSessionManager를 사용하는 정도만 알면 될 것 같아 다음처럼 수정했다.


```java
@Profile("test")
@Component
public class TestUserSessionManager implements UserSessionManager {
    private static final String USER_SESSION_KEY = "ACCESS_USER";
    private Map<String, AccessUser> accessUserMap = new HashMap<>();

    public void saveUser(AccessUser accessUser) {
        accessUserMap.put(USER_SESSION_KEY, accessUser);
    }

    public AccessUser extractUser() {
        return accessUserMap.get(USER_SESSION_KEY);
    }
}
``` 


그리고 테스트의 given 절을 추가했다

```java
    @DisplayName("글 제목, 글 내용을 작성하면 유저 ID 정보까지 같이 저장된다")
    @Test
    void write() {
        //given
        userSessionManager.saveUser(new AccessUser("a1010100z")); // 세션에 저장ㅇ
        ArticleUpdateRequestDto requestDto = new ArticleUpdateRequestDto("글 제목", "글 내용");

        //when
        boardService.write(requestDto);


        //then
        assertThat(articleRepository.findByUserId("a1010100z")).isNotNull();
    }
```



BoardService 게시글작성 테스트에서는 UserSessionManager 인터페이스로 얻어온 User의 정보를 함께 저장되는 것을 확인했다.

실제 UserSessonManager만 정상적으로 동작함이 확인된다면 BoardService 역시 실제 Session 을 사용해도 정상 작동됨을 믿을 수 있따.



AccessUserSessionManager는 HttpServletRequest를 직접적으로 의존한다. 여기서 MockHttpServletRequest를 쓸 수 있겠다.

```java
@SpringBootTest(classes = AccessUserSessionManager.class)
class AccessUserSessionManagerTest {

    MockHttpServletRequest servletRequest;
    AccessUserSessionManager userSessionManager;

    @BeforeEach
    void setUp() {
        MockHttpSession httpSession = new MockHttpSession();
        servletRequest = new MockHttpServletRequest();
        servletRequest.setSession(httpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
        userSessionManager = new AccessUserSessionManager(servletRequest);
    }

    @DisplayName("유저를 저장하면 세션에서 꺼내올 수 있다.")
    @Test
    void sessionSave() {
        userSessionManager.saveUser(new AccessUser("a1010100z"));
        assertThat(((AccessUser) servletRequest.getSession().getAttribute("ACCESS_USER")).getUserId())
                .isEqualTo("a1010100z");
    }

}
``` 

