
빈 주입 방식
1. 생성자: 인스턴스를 만들면서 주입을 시도.  
2. Setter, Field -> required = false로 인스턴스 자체는 만들어지게 할 수 있음  
Setter로 주입을 받으면 BookService 자체의 인스턴스는 만들어지고 의존주입을 하려고 시도한다.
그런데, 그 과정을 실패할 수 있다. 
생성자는 올바르지 않은 의존주입 시 생성부터 실패해서 직관적, Setter는 X 


---

Q. interface로 만들고 구현체 2개가 모두 Bean 이면 주입은? 
A. 주입을 못시켜준다. 


해결방법)
1. @Primary  - 추천! 근데 빈마다 다른 빈이 필요할 땐 ,,? 
2. 모든 Bean을 다 받는다 - 개신기해! 
- List<BookRepository> bookRepositories 
3. @Qualifier
4. 이름으로. BookRepository jaeyeonBookRepository 
- 왜 비추 ,,? TypeSafe 하지 않다고 말씀하시는 덧 ,,! 


동작원리 BeanPostProcessor, InitializingBean 
Bean Instance 생성 시 부가적인 작업 
---> @PostConstructor 여기서는 이미 모든 의존 빈이 주입된 상태 
InitializingBean 에서 before 단계  
이 과정에서 @Autowired 된 빈을 주입해준다.

BeanFactory 에서 AutowiredAnnotationBeanPostProcessor 빈을 찾고,
그 빈은 일반적인 Bean 을 찾아서 Autowired 를 처리한다!    