package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.ClientService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        
        AppConfig appConfig = new AppConfig();
        
        // 1. 조회: 호출할 때 마다 객체를 생성
        ClientService clientService1 = appConfig.clientService();
        
        // 2. 조회: 호출할 때 마다 객체를 생성
        ClientService clientService2 = appConfig.clientService();

        // 두 객체의 참조 값이 다르다.
        System.out.println("clientService1 = " + clientService1);
        System.out.println("clientService2 = " + clientService2);

        // clientService1 != clientService2
        assertThat(clientService1).isNotSameAs(clientService2);
    }
}
