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



