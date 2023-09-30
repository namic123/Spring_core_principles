package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Client;
import hello.core.member.Grade;
import hello.core.member.MemoryClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {
    @Test
    @DisplayName("생성자 주입 예제")
    void createOrder(){
        MemoryClientRepository clientRepository = new MemoryClientRepository();
        clientRepository.save(new Client(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(clientRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
