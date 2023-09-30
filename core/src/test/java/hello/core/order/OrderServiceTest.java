package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Client;
import hello.core.member.ClientService;
import hello.core.member.Grade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    ClientService clientService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        clientService = appConfig.clientService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder(){
        Long memberId = 1L;
        Client client = new Client(memberId, "memberA", Grade.VIP);
        clientService.join(client);

        Order order = orderService.createOrder(memberId, "itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
