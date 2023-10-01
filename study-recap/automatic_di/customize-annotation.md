# 사용자 정의 애노테이션 

## 사용자 정의 애노테이션을 만드는 이유?
* @Qualifier("") 에너테이션 안에 들어가는 문자열은 컴파일 시점에서 문자열 인식이 안되는 문제가 있을 수 있다.
* 또는 문자를 잘못 입력해도 오류가 발생할 수 있다.
* 이럴때 사용자 정의 애노테이션을 만들어서 코드의 정확성을 높이는 방법을 활용할 수 있다.

## @Qualifier 에너테이션 만들기.
1. shift+shift 하면 여러가지 소스파일을 검색할 수 있음. 여기서 @Qualifier 검색
2. Qualifier의 소스코드를 보면 여러가지 에너테이션으로 만들어진 것을 볼 수 있다. 여러 에너테이션들을 복사해서 
만드려는 에너테이션에 그대로 복사.
3. 그리고 Qualifier를 마지막에 붙여줌.

예시

## 에노테이션 직접 만들기
```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
```

## 사용자 정의 애노테이션 활용
```java
@Component
@MainDiscountPolicy // 이 에노케이션 안에 Qualifier("mainDiscountPolicy")가 지정되어 있음.
public class RateDiscountPolicy implements DiscountPolicy{
    }
    
    
////////////////////////////////////////
@Autowired
public OrderServiceImpl(ClientRepository clientRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {  // 여기에도 에너테이션 적용
    this.clientRepository = clientRepository;
    this.discountPolicy = discountPolicy;
}
```

* 여기서는 Qualifier를 재정의했지만 다른 애너테이션(예:@Autowired)도 직접 정의해서 활용할 수 있다는 점 잊지말자.
