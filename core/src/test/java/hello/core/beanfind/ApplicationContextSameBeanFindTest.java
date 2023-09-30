package hello.core.beanfind;


import hello.core.member.ClientRepository;
import hello.core.member.MemoryClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
@Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류 발생")
    void findBeanByTypeDupliccate(){
//    ClientRepository bean = ac.getBean(ClientRepository.class); // 예외 발생, ac 즉, SameBeanConfig에는 같은 타입이 두개 있어서 중복 오류 발생
    // 중복 타입 중 어떤 빈으로 선택할지 애매하기 떄문

    // 예외가 터져야 성공되는 테스트 케이스
    assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(ClientRepository.class));
}


//@Test
//@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
//void findBeanByName(){
//    ClientRepository clientRepository = ac.getBean("clientRepository", ClientRepository.class);// 중복 타입이 있으므로, 빈 이름으로 탐색
//    assertThat(clientRepository).isInstanceOf(ClientRepository.class);
//}

    @Test
    @DisplayName("중복 타입을 모두 조회하기")
    void findAllBeanByType(){
    // getBeansOfType을 쓰면 중복되는 타입의 빈을 모두 가져옴
        // 즉, MemberRepository의 타입을 가진 빈을 모두 가져온다.
        // Map 형태로 반환되는데, Key에는 해당 빈의 "이름"이 되고 value는 해당 빈의 인스턴스기 자징
        Map<String, ClientRepository> beansOfType = ac.getBeansOfType(ClientRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = "+beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
}


    @Configuration
    static class SameBeanConfig {    // 같은 타입이 두개 있는 빈
        @Bean
        public ClientRepository memberRepository1() {
            return new MemoryClientRepository();
        }

        @Bean
        public ClientRepository memberRepository2() {
            return new MemoryClientRepository();
        }
    }
}
