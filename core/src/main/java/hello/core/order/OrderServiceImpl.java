package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Client;
import hello.core.member.ClientRepository;

public class OrderServiceImpl implements OrderService{   // 주문 서비스 구현체
    private final ClientRepository clientRepository; // 회원 저장소 객체
    private final DiscountPolicy discountPolicy;  // 할인 정책 인터페이스 (역할)

    public OrderServiceImpl(ClientRepository clientRepository, DiscountPolicy discountPolicy) {
        this.clientRepository = clientRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {   // 주문 생성
        Client client = clientRepository.findById(memberId);        // 저장소 id를 찾아서 반환
        int discountPrice = discountPolicy.discount(client, itemPrice);  // 할인 정책

        return new Order(memberId, itemName, itemPrice, discountPrice); // Order 객체 반환
    }
}
