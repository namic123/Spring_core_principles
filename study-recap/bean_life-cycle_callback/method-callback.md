# 빈 등록 초기화, 소멸 메서드 (설정 정보 옵션)!

## 설정 정보에 아래 코드처럼 초기화, 소멸 메서드를 지정할 수 있음.

### 빈 클래스에 아래 코드처럼 초기화, 소멸 콜백 메서드를 지정해주고, 빈 에너테이션에 설정 정보를 넣어준다.
```java
public class NetworkClient {
    // 관례상 많이 사용되는 이름
// 초기화 콜백
    public void init() {
        // 의존 관계 주입이 끝나면 호출하겠다는 메서드
        connect();
        call("초기화 연결 메시지");
    }

    // 관례상 많이 사용되는 이름
// 소멸 콜백
    public void close() {
        disconnect();
    }
}
        
        //////////////////////////////////
        @Bean(initMethod = "init", destroyMethod = "close") // 옵션을 준다.
        public NetworkClient networkClient(){}
```
* 초기화 콜백은 initMethod = ""
* 소멸 콜백은 destroyMethod = ""
* @Bean 에너테이션에 옵션을 붙이는 것이니 당연하지만, 수동 빈 등록시에만 사용 가능한 방법.

## 특징
* 메서드 이름을 자유롭게 줄 수 있음(위에는 init, close라고 지었지만 자유임)
* 스프링 빈이 스프링 코드에 의존하지 않음.
* 따라서, 코드가 아니라 설정 정보를 사용하므로, 코드를 고칠 수 없는 외부 라이브러리에도 초기화 종료 메서드를 적용할 수 있음.


## 참고! 종료 메서드의 추룐~
* @Bean(destroyMethod) : destroyMethod는 특별한 기능이 있음
* 일반적으로 대부분 라이브러리의 종료 메서드는 "close"와 "shutdown"이라는 이름을 많이쓴다.
* @Bean destroyMethod의 기본값이(inferred) (즉, 추론)으로 되어 있는데, 
* 이 기능은 close,shutdown이라는 이름의 메소드를 자동으로 호출해주는 기능을 갖고있다.
* 따라서, 직접 스프링 빈으로 등록하면 종료 메서드를 따로 적지 않아도 잘 동작함.
