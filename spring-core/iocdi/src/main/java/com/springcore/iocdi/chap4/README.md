
### @ComponentScan
해당 패키지부터 하위 패키지의 빈대상 전부 스캔

@Filter 옵션. 아래를 Default 로 걸러준다. 
- excludeFilter 
-- TypeExcludeFilter
-- AutoConfigurationExcludeFilter 


**중요한 건 이 한 줄**
<u>어디부터 어디까지 스캔할 것인지. 그리고 무엇을 걸러낼 것인지</u>


@Component: 애플리케이션 구동 초기에 모든 빈을 만든다.  
- @Repository
- @Service
- @Controller
- @Configuration

BeanFactoryPostProcessor - 다른 빈들을 만들기 전에 @ComponentScan 을 먼저 한다 


---> 구동성능과 관계가 있을 수 있음 : Functional 을 사용한 빈등록 방법. 리플렉션이나 cglib 같은 프록시기법을 사용하지 않기 떄문에 구동성능 상 이점이 있을 수 있음

 