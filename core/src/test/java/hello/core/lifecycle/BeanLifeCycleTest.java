package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//### 외부 네트워크에 미리 연결하는 객체를 하나 생성한다고 가정.(실제 네트워크 연결이 아닌 단순히 문자만 출력)
//* 클라이언트는 애플리케이션 시작 시점 connect()를 호출해서 연결을 맺어두야하고,
//* 종료시점에 disConnect()를 호출해서 연결을 끊어야 한다고 가정.

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // close를 쓰려면 ConfigurableApplicationContext를 써야함. ApplicationContext는 지원하지 않음.
        // 빈을 명시적으로 close하는 이유는 리소스 해제, 종료 작업 수행, 빈의 생명주기 관리 등의 이유가 있음.
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
        return networkClient;
        }
    }
}
