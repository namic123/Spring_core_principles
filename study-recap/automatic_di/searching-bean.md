# 의도적으로 해당 타입의 스프링 빈이 다 필요한 경우

```java
public class AllBeanTest {

@Test
@DisplayName("타입의 모든 빈 찾기")
    void findAllBean(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
// AutoConfigApp도 등록한 이유는 아래 DiscountService는 본인만 스프링빈이기 때문에
// DiscountPolicy 타입 빈을 가져오려면 빈을 포함하고 있는 AutoAppConfig도 같이 빈으로 등록해준다.
DiscountService discountService = ac.getBean(DiscountService.class);
Client client = new Client(1L, "userA", Grade.VIP);
int discountPrice = discountService.discount(client, 10000, "fixDiscountPolicy");
    assertThat(discountService).isInstanceOf(DiscountService.class);
    assertThat(discountPrice).isEqualTo(1000);

    int rateDiscountPrice = discountService.discount(client,20000,"rateDiscountPolicy");
    assertThat(rateDiscountPrice).isEqualTo(2000);
}


// 테스트를 위한 빈
static class DiscountService{
    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policies;

    @Autowired
    public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
        this.policyMap = policyMap;
        this.policies = policies;
        System.out.println("policyMap = " + policyMap);
        System.out.println("policies = " + policies);
    }
    public int discount(Client client, int price, String discountCode){
        DiscountPolicy discountPolicy = policyMap.get(discountCode);
        return discountPolicy.discount(client, price);
    }
}
}
```

### 로직 분석
* DiscountServie는 Map으로 모든 DiscountPolicy 타입의 객체를 받는다.
* 이때 fix와 rateDiscountPolicy가 주입됨.

* discount() 메서드는 discountCode로 "fixDiscountPolicy"가 넘어오면 map에서 fixDiscountPolicy 스프링 빈을 찾아서 실행
* 마찬가지로 rateDiscountPolicy가 넘어오면 fixDiscountPolicy 스프링 빈을 찾아서 실행


### 주입 분석
* Map <String, DiscountPolicy> : map의 키에 스프링 빈의 이름을 넣어주고 그 값으로 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담음
  * 즉, 키가 스프링 빈의 이름이되고 value가 빈의 객체가 됨.
* List<DiscountPolicy>: DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
* 만약 해당 타입 빈이 없으면 빈 컬렉션이나 Map을 주입


## *참고* 스프링 컨테이너를 생성하면서 스프링 빈 등록하기
* 스프링 컨테이너는 생성자에 클래스 정보를 받는다. 여기에 클래스 정보를 넘기면 해당 클래스가 스프링 빈으로 자동 등록된다.

예
```java
new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
```
