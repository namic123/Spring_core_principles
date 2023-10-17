# 컴포넌트 스캔과 의존관계 자동 주입

### 등록해야 할 빈(@Bean)이 몇백 몇천개라면?
- 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔 기능을 제공
- 의존 관계도 자동 주입하는 @Autowired도 제공



### @ComponentScan
- 자동으로 스프링 빈을 끌어올리는 에너테이션
- 즉 자동으로 스프링 빈을 등록해줌
- 이 에너테이션은 @Component 에너테이션이 붙은 클래스를 모두 스캔해서 스프링 빈으로 등록

@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
- 스프링 빈을 자동 등록하는데 필터링 할 것을 지정
- 컴포넌트 스캔을 지정하면 @Configuration이 붙은 설정 정보도 스프링 빈으로 등록함.
- 왜냐하면, @Configuration 소스 코드를 열어 보면 @Component가 붙어 있기 때문에 ComponentScan 대상이됨.
- 따라서 여기서 @Configuration 에너테이션이 붙은 것을 빼게 설정(기존에 작성한 AppConfig의 빈과 충돌이 될 수 있으므로, 뻇음)



### @Component
- ComponentScan의 대상이 될 클래스에 지정
- 이 에너테이션이 붙은 클래스는 ComponentScan에 의해 스프링 빈이 될 대상
- 구현체에 지정하면 됨


### 컴포넌트 스프링 빈 등록의 주의점
- 스프링 빈의 기본 이름은 클래스명을 사용하되. "맨 앞글자는 소문자"
- 스프링 빈 이름 지정도 가능 예: @Component("이름 지정 예시"), 단 구현체에 지정
- 왠만하면 Default 명으로 사용하는 것이 좋음


### @Autowired
- 자동으로 의존관계를 주입해주는 에너테이션
- 스프링 빈 클래스의 생성자에 지정한다.
- 스프링 컨테이너가 생성자 정보를 보고 의존 관계를 주입한다.


### 정리
- 설정 클래스가 될 클래스에 @Configuration과 흩어져있는 스프링 빈이 될 대상을 긁어오기 위해 @ComponentScan을 지정
- 스캔 시에 제외할 대상을 지정하는 것도 가능.
- @Component 에너테이션은 ComponentScan의 대상이 될 구현체에 선언한다.
- 즉 이 에너테이션이 붙은 클래스는 ComponentScan이 붙은 설정 클래스의 스프링 빈으로 자동 등록된다.
- @Autowired는 스프링 빈이 될 클래스의 생성자에 붙이며, 이 에너테이션이 붙으면 스프링에서 자동으로 의존 관계를 주입해준다.