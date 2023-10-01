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
### 1. 타입 매칭 
### 2. 타입 매칭의 결과가 2개 이상일 때 필드 명으로 빈 이름을 매칭