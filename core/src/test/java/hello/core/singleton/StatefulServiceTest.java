package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;


// 상태 유지 싱글톤 test 케이스
public class StatefulServiceTest {

    @Test
    @DisplayName("상태 유지 싱글 톤의 문제점.")
    void statfulServiceSinglton(){
        // 스프링 컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

       // statefulService 빈 객체 1,2 저장
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : A 사용자 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB : B 사용자 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        // ThreadA : 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        // 왜냐하면 싱글톤 컨테이너이기 때문에 객체를 공유함.
        System.out.println("price = " + price);

        // statefulService1의 price값이 20000이 맞는지 test
        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
