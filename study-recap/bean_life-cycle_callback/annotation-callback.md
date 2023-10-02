# 빈 생명 주기 콜백 애노테이션 방식 (가장 많이 사용됨)
* 가장 많이 사용되는 방식
* 초기화 콜백 -> @PostConstruct
* 소멸 콜백 -> @PreDestory

## 방법이 굉장히 간단, 메소드에 에너테이션만 붙여주면됨.
```java
public class NetworkClient {
    @PostConstruct  // 빈생명주기 '초기화 콜백' 에너테이션 방식
    public void init() {
        // 의존 관계 주입이 끝나면 호출하겠다는 메서드
        connect();
        call("초기화 연결 메시지");
    }
    
    @PreDestroy // 빈생명주기 '소멸 전 콜백' 에너테이션 방식
    public void close() {
        disconnect();
    }
}
```

# 특징
* 최신 스프링에서 가장 권장하는 방법
* 편리함.
* 패키지가 javax.aannotation.PostConstruct이다.
* 즉, 스프링에 종속적이지 않고 자바 표준. 따라서, 스프링이 아닌 다른 컨테이너에서도 동작한다.
* @ComponentScan과 잘 어울린다 (자동 빈 등록 방식)
* 유일한 단점은 외부 라이브러리에는 적용 불가. 
* 외부 라이브러리를 초기화 종료 해야하면 @Bean의 기능을 사용하자

# 정리
* 기본적으로 @PostConstruct, @PreDestory를 사용하자
* 외부 라이브러리 초기화, 종료가 필요하다면 @Bean(initMethod, destroyMethod)를 사용하자.