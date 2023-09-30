package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.ClientRepository;
import hello.core.member.ClientServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


// 참고 내용. @Bean이 붙은 메서드에 static이 포함되는 경우 싱글톤을 보장하지 않는다.
// 즉 아래 테스트 케이스를 예로들어 AppConfig에 clientRepository에 static이 붙는 경우 싱글톤이 유지되지 않을 수 있다.

public class ConfigutationSingletonTest {

    @Test
    @DisplayName("@Configuration을 붙여야 스프링 컨테이너가 싱글톤 패턴을 유지할 수 있다.")
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 빈 저장
        ClientServiceImpl clientService = ac.getBean("clientService", ClientServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        ClientRepository clientRepository = ac.getBean("clientRepository", ClientRepository.class);

        // 모두 같은 인스턴스를 참조
        System.out.println("clientService.getClientRepository() = " + clientService.getClientRepository());
        System.out.println("orderService.getClientRepository() = " + orderService.getClientRepository());
        System.out.println("clientRepository = " + clientRepository);

        // 모두 같은 인스턴스를 참조하는지 테스트

        // 테스트 성공, isSameAs는 같은 객체인지 비교하는 메서드
        assertThat(clientService.getClientRepository()).isSameAs(clientRepository);
        // 테스트 성공, isSameAs는 같은 객체인지 비교하는 메서드
        assertThat(orderService.getClientRepository()).isSameAs(clientRepository);
    }
}
