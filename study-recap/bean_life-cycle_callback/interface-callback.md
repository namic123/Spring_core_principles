# 빈 생명주기 콜백 인터페이스 방법 (InitializingBean, DispoableBean)
* 스프링 빈이 될 대상 클래스에 InitializingBean, DisposableBean 인터페이스를 구현한다.
* InitializingBean, DisposableBean이 가진 메서드를 오버라이드 해야한다.
* InitializingBean은 의존관계 주입이 완료되고 동작하는 afterPropertiesSet() 메서드를 가진 인터페이스이고
* DisposableBean은 스프링 빈 소멸 전에 동작하는 destroy() 메서드를 가진 인터페이스이다.

```java
public class NetworkClient implements InitializingBean, DisposableBean {
    private String url; // 접속 url
    public NetworkClient(){     // 기본 생성자
        System.out.println("생성자 호출, url = " + url);
        }

    public void setUrl(String url) {
        this.url = url;
    }  

    // 서비스 시작시 호출.
    public void connect(){
        System.out.println("connect : " + url);
    }
    public void call(String message){
        System.out.println("call: " + url+ " message = "+message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }

    // InitializingBean 인터페이스의 추상 메서드
    // 의존 관계 주입 끝남과 동시에 호출되는 메소드
    @Override
    public void afterPropertiesSet() throws Exception {
        // 의존 관계 주입이 끝나면 호출하겠다는 메서드
        connect();
        call("초기화 연결 메시지");
    }
// DisposableBean 인터페이스의 추상 메서드
    // 빈이 소멸되기 전 바로 호출되는 메서드
    @Override
    public void destroy() throws Exception {
     disconnect();   
    }
}
```

## 초기화, 소멸 인터페이스의 단점
1. 스프링 전용 인터페이스 의존성
* 이 인터페이스들은 스프링에 특화된 인터페이스이므로, 이를 구현하면 빈 클래스가 스프링에 의존성을 가지게된다.
* 이는 빈 클래스를 '스프링 외부'에서 재사용하거나 테스트하기 어렵게 만듬.
* 스프링 프레임 워크에 의존성을 최소화하고 POJO(순수 자바)스타일을 유지하는 것이 바람직하다.

2. 초기화 소멸 메서드의 이름을 변경할 수 없다.
* InitializingBean -> afterPropertiesSet(), DisposableBean -> destroy()

3. 외부 라이브러리에 적용할 수 없다.

### 인터페이스를 사용하는 초기화 종료 방법은 옛날에 나온 방식, 현재는 잘 사용되지 않는 방법들이다.