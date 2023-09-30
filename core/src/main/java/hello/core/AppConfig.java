package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.ClientService;
import hello.core.member.ClientServiceImpl;
import hello.core.member.MemoryClientRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 설정 정보에 붙이는 에너테이션
public class AppConfig {        // 의존성 주입
    @Bean
    public ClientService clientService(){
        return new ClientServiceImpl(clientRepository());
    }
    @Bean
    public static MemoryClientRepository clientRepository() {
        return new MemoryClientRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(clientRepository(), discountPolicy());
    }
    @Bean
    public static DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
