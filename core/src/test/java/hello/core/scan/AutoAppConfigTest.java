package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.ClientService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    @DisplayName("자동으로 스프링 빈이 등록됐는지 확인")
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        ClientService clientService = ac.getBean(ClientService.class);
        assertThat(clientService).isInstanceOf(ClientService.class);
    }
}
