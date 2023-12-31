# 컴포넌트 스캔의 필터 활용

### includeFilters
### excludeFilters

### 우선 사용자 정의 에너테이션을 하나 만든다.

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}


and

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
```

* 위에 붙은 여러 가지의 에너테이션은 @Component의 소스코드에서 가져온 것

* 그 다음에 빈이 될 클래스에 해당 어노테이션을 붙인다.
* 예 : @MyIncludeComponent

## 설정 클래스에 아래와 같이 필터 할 수 있음

``` java
    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    ) 
```
- includeFilters 는 포함할 필터
- 반대로 excludeFilters는 불포함할 필터



### FilterType의 옵션
- ANNOTATION: 기본값, 에너테이션을 인식해서 동작
  - 기본값이므로 제외 가능

- ASSIGNABLE_TYPE : 지정 타입과 자식 타입을 인식해서 동작

- ASPECTJ : AspectJ 패턴 사용

- REGEX : 정규 표현식

-CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리


### 권장사항 *
* 기본 설정에 최대한 맞추어 설계하는 것을 권장.
- 코드의 복잡성 때문