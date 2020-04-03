
## Interceptor와 AoP 써보기


1. User 로그인 시 User가 관리자/일반 유저에 따라 다른 로깅을 한다.

2. User가 게시글 저장을 하기 전에 관리자면 공지사항 마크를, 아니면 그대로 저장한다. 


## 기록
1. Interceptor에서 HttpServletRequest는 ByteStream으로 들어온다. Input Dto 를 매핑하는 깔끔한 코딩을 해보자.
