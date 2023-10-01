# 조회 대상 빈이 2개 이상일 때 해결 방안
* ## @Autowired 필드명 매칭
* ## Quilifier -> @Quilifier끼리 매칭 -> 빈이름 맻
* ## @Primary 사용


## @Autowired 필드명 매칭
* 예시 코드
```java
    @Autowired
    public OrderServiceImpl(ClientRepository clientRepository, DiscountPolicy discountPolicy) {
        this.clientRepository = clientRepository;
        this.discountPolicy = discountPolicy;
    }
```
* 예시 상황 
 * discountpolicy의 구현체인 정률과 정액 할인이 빈에 등록됐다고 가정해보자
 * 이 경우에는 오류가 발생할 것이다. (위 생성자에서 DiscountPolicy는 하나만 받을 수 있는데 빈은 2개 이상 존재하기 때문에)
 * @Autowired에는 특별한 기능이 있다. 생성자의 매개 변수명을 대상 조회 빈의 이름으로 쓰면 @Autowired가 자동으로 매칭해준다.

```java
    @Autowired
    public OrderServiceImpl(ClientRepository clientRepository, DiscountPolicy rateDiscountPolicy) {
        this.clientRepository = clientRepository;
        this.discountPolicy = rateDiscountPolicy;   // 대상 조회 빈의 이름으로 변경.
    }
```
* 타입이 아닌 변수명을 바꿨는데, 대상 조회 빈과 매칭됐다. 
* 이것이 @Autowired가 가진 기능.

## @Autowired 매칭 정리
### 1. 타입 매칭  - 타입이 하나면 그 타입과 매칭됨.
### 2. 타입 매칭의 결과가 2개 이상일 때 파라미터 명으로 빈 이름을 매칭


-----------------------------------------------------
## @Qualifier
* 추가 구분자를 붙여주는 방법. 
* 주입시 추가적인 방법을 제공하는 것, 빈 이름을 변경하는 것이 아니다.

# 구현체 RateDiscountPolicy에 @Qualifier("thisIsRateDiscount")라고 구분자를 붙였다고 가정한다.
* 생성자에 아래와 같은 방법으로 @Qualifier를 추가해주면 된다.
```java
    @Autowired
    public OrderServiceImpl(ClientRepository clientRepository, @Qualifier("thisIsRateDiscount") DiscountPolicy DiscountPolicy) {
        this.clientRepository = clientRepository;
        this.discountPolicy = DiscountPolicy;   // 여전히 역할에만 의존하도록 둔다.
    }
```
* 위 코드처럼 구현체에 붙인 @Qualifier 명대로 파라미터에 붙여주기만 하면 된다.

### Qualifier 정리
* @Qualifier 끼리 매칭
* 빈 이름 매칭 (혹시라도 만약에 Qualifier 이름으로 빈이 찾아진다면, Qualifier의 이름으로 추가되어 있는 스프링 빈이 있는지 찾느다)
  * 주의: Qualifier는 주입에서 추가적인 방법을 제공하는 것이지 Qualifier에 지정한 이름의 스프링 빈이 등록이 되는 것이 아니다.
* 검색이 안된다면 NoSuchBeanDefinitionException 예외 발생


---------------------------------------------------------------
## @Primary 사용 - (자주 사용되는 방법)
* @Primary는 '우선 순위를 지정하는 방법이다.'
* @Autowired 시에 여러 빈이 매칭되면 @Primary가 붙은 빈이 우선권을 가진다.
* 특정 구현체 빈에 우선권을 주고 싶으면 @Primary 에너테이션을 붙인다.

예시 
```java
@Component
@Primary    // 우선권을 부여
public class RateDiscountPolicy implements DiscountPolicy{}
```

### @Qualifier를 쓸까? @Primary를 쓸까?
* @Qualifier는 주입 받을 떄 이 에너테이션을 일일이 붙여야한다.
* 자주 사용되는 것을 @Primary로 하고 가끔 사용되는 것은 @Qualfiier를 쓴다.

### 우선순위
* @Primary는 기본 값처럼 동작하고, @Qualifier는 매우 상세하게 동작한다.
* 스프링에서 대부분 자세한 것이 우선 순위가 높다
* 따라서 여기서도 @Qualifier가 우선순위가 @Primary 보다 높다.