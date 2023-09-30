package hello.core.beanfind;
import hello.core.member.ClientService;
import hello.core.member.ClientServiceImpl;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    // getBean 메서드의 1번 파라미터에는 bean이름이 들어가고 2번 파라미터에는 타입을 입력한다.

    // bean 이름으로 조회하는 방법
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        ClientService clientService = ac.getBean("clientService", ClientService.class);
        assertThat(clientService).isInstanceOf(ClientServiceImpl.class);
    }

    // 타입으로만 조회
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        ClientService clientService = ac.getBean(ClientService.class);  // 인터페이스
        assertThat(clientService).isInstanceOf(ClientServiceImpl.class);
    }

    // 구현 타입으로 조회
    @Test
    @DisplayName("구현 타입으로 조회")
    void findBeanByName2(){
        ClientService clientService = ac.getBean("clientService", ClientServiceImpl.class);  // 구현체
        assertThat(clientService).isInstanceOf(ClientServiceImpl.class);
    }

    // 위 세가지 테스트 케이스는 빈 이름, 인터페이스 타입 또는 구현체 타입으로 빈을 조회하는 테스트를 보여준다.
    // 구체 타입으로 조회하는 것은 좋지 않다. 역할과 구현을 분리 해야하기 때문(좋은 객체지향 설계 원칙)

    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByNameX(){
//        ac.getBean("xxxxx",ClientService.class);    // xxxx 빈을 찾으라는 것. 당연히 예외 발생(NoSuchBeanDefinitionException)
      assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean("xxxxx", ClientService.class));
      // 위 코드는 ac.getBean() 을 샐행했을때 NoSuchBeanDefinitionException 예외가 발생한다면 테스트 성공

    }
}
