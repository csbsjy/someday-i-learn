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


이 과정에서 게시글작성 통합테스트, BoardService 의 단위테스트를 작성할 때 문제가 발생했다.
통합테스트는 User 의 로그인세션이 없으면 아예 진행조차 할 수 없었고, boardservice 에서 게시글작성 시 게시글의 유저정보는 NULl 로 들어가기 때문이다.


세션이 있다고 치고 테스트코드를 작성할 수 없을까 하다가 MockHttpSession에 대해 알게되었다. 

먼저 BoardService 의 "게시글작성"에 대한 단위테스트를 진행해보자. 
