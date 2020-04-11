## HttpSession 을 이용한 로직을 테스트하는 방법

로그인 이후 세션에 저장하는 로직을 단위테스트짜고 싶어서 찾아보다가 MockHttpSession, MockHttpServletRequest를 발견했다.
앞으로도 자주 쓰일 클래스일 것 같아서 정리를 해두려고 한다. 


MockHttpSession의 Java doc 을 보면 HttpSession 을 구현한 클래스임을 알 수 있다. 
(https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mock/web/MockHttpSession.html)
 
![image](https://user-images.githubusercontent.com/47847993/79041986-efb24900-7c2e-11ea-9f12-1cd86bd8399f.png)


