# 의존관계 자동 주입

### 스프링의 두 가지 라이프 사이클
-빈 생성 단계
-의존 관계 주입 단계

### 의존 관계의 여러가지 주입 방법
1. 생성자 주입
2. 수정자 주입(setter)
3. 필드 주입
4. 일반 메서드 주입


----------------------------------------------------------------------------------------
1. 생성자 주입 ( 가장 많이 사용되는 주입 방식 )
* @Autowired, 생성자에 이 에너테이션을 붙여서 생성자의 정보를 토대로 의존 관계를 주입
* 생성자 호출시점에 딱 1번만 호출되는 것이 보장됨.
* 즉, 코드만 잘 짜면 1번만 호출하여 의존 관계를 주입하고 더 이상 세팅 못하게 막는 것이 가능하다.
* 따라서, 불변, 필수 의존관계에 사용된다.

    * 수정이 적은 불변성 코드를 작성하는 것이 개발에서 좋은 습관이다.

* 불변, 필수 의존 관계란?? 
  * final이 붙은 필드는 값이 무조건 초기화되어야 하며, 값이 할당 되면 "불변"이다.
  * 또한, 생성자는 무조건 호출 시에 "필수로" 값이 할당되어야 한다.
  * 이러한 것을 불변, 필수 의존 관계라고함.


**중요** 생성자가 단 하나만 있을 때는 @Autowired를 생략이 가능하다.
- 스프링 컨테이너에서 자동으로 생성자를 찾아서 의존성을 주입해줌.


----------------------------------------------------------------------------------------

2. 수정자 주입
* 메서드 형식으로 메서드명에 'set'이 붙고 그 뒤에는 필드의 이름이 붙는다.(예: setXXXX) (자바 빈 프로퍼티 규약)
* 보통 수정자를 setter라고 하는데 setter에 @Autowired를 선언해서 의존성을 주입하는 것도 가능하다.

*참고* 수정자 주입을 사용할 때 필드는 final이 붙으면 안된다.

*수정자 주입 특징*
 * 선택적인, 변경 가능성이 있는 의존관계에 적용
 * @Autowirde(required = false)를 쓰면 선택적으로 의존성 주입 가능

@Autowired의 기본 동작은 주입 대상이 없으면 오류 발생. 주입 대상이 없어도 동작을 원할 경우 @Autowired(required = false)로 설정
----------------------------------------------------------------------------------------\

### 자바빈 프로퍼티 규약이란???
- 필드의 값을 변경하지 않고 getter, setter 메서드로 값을 읽거나 수정하도록 권장하는 규약

--------------------------------------------------------------------------------

3. 필드 주입
- 이름에서 유추 할 수 있듯이 필드 자체에 @Autowired를 붙이는 것 (예 : @Autowired private SomeClass someclass;)
- 코드가 간결하지만 권장하지 않는 방법이다. (안티 패턴)
- 외부에서 변경이 불가능해서 테스트를 하기가 굉장히 어렵다.
- 정말 필요한 경우가 아니면 사용하지 말자.

* 사용되는 일반적인 경우?
  * 애플리케이션 코드와 전혀 관련없는 테스트 코드에서는 사용 가능.

----------------------------------------------------------------------------------------

4. 일반 메서드 주입 
* 이름 그대로 일반 메서드를 통해서 주입 받을 수 있음
* 한번에 여러 필드를 주입 받을 수 있음
* 일반적으로는 잘 사용하지 않는다.


예시
<pre>
<code>
    @Autowired  // 일반 메서드 주입, 생성자랑 비슷하게 생김
    public void init(ClientRepository clientRepository, DiscountPolicy discountPolicy){
        this.clientRepository = clientRepository;
        this.discountPolicy = discountPolicy;
    }
</code>
</pre>

* 관례상 init이라는 명으로 시작됨.


******잊지 않기 위한 참고****** 당연한 이야기지만 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다.
스프링 빈이 아니면 @Autowired를 적용해도 동작하지 않는다.