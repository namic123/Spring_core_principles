package hello.core;

import hello.core.member.Client;
import hello.core.member.ClientService;
import hello.core.member.Grade;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        ClientService clientService = appConfig.clientService();  // 회원 가입, 조회 구현체
//        OrderService orderService = appConfig.orderService();     // 주문 생성 구현체
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ClientService clientService = applicationContext.getBean("memberService", ClientService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;         // 회원 아이디
        Client client = new Client(memberId, "memberA", Grade.VIP); // 회원 정보 생성
        clientService.join(client); // 회원 가입

        Order order = orderService.createOrder(memberId,"itemA", 30000);    // 주문 생성, O rder 객체를 반환
        System.out.println("order = " + order); // 주문 정보
        System.out.println("order. = " + order.calculatePrice());   // 할인 정책

    }
}
