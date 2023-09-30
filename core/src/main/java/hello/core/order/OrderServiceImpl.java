package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Client;
import hello.core.member.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {   // 주문 서비스 구현체
    private ClientRepository clientRepository; // 회원 저장소 객체
    private DiscountPolicy discountPolicy;  // 할인 정책 인터페이스 (역할)

//    @Autowired // setter를 통한 의존성 주입
//    public void setClientRepository(ClientRepository clientRepository) {
//        this.clientRepository = clientRepository;
//    }
//    @Autowired  // setter를 통한 의존성 주입
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

//
//    @Autowired  // 일반 메서드 주입, 생성자랑 비슷하게 생김
//    public void init(ClientRepository clientRepository, DiscountPolicy discountPolicy){
//        this.clientRepository = clientRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired  // 생성자를 통한 의존성 주입
    public OrderServiceImpl(ClientRepository clientRepository, DiscountPolicy discountPolicy) {
        this.clientRepository = clientRepository;
        this.discountPolicy = discountPolicy;
    }

    // 테스트 용도
    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {   // 주문 생성
        Client client = clientRepository.findById(memberId);        // 저장소 id를 찾아서 반환
        int discountPrice = discountPolicy.discount(client, itemPrice);  // 할인 정책

        return new Order(memberId, itemName, itemPrice, discountPrice); // Order 객체 반환
    }
}
