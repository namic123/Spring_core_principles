package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 자바 코드
//외부 네트워크에 미리 연결하는 객체를 하나 생성한다고 가정.(실제 네트워크 연결이 아닌 단순히 문자만 출력)
//* 클라이언트는 애플리케이션 시작 시점 connect()를 호출해서 연결을 맺어두야하고,
//* 종료시점에 disConnect()를 호출해서 연결을 끊어야 한다고 가정.
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

    @Override
    public void afterPropertiesSet() throws Exception {
        // 의존 관계 주입이 끝나면 호출하겠다는 메서드
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
     disconnect();
    }
}
