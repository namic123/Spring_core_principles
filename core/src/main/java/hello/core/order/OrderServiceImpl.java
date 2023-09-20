package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{   // 주문 서비스 구현체
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 회원 저장소 객체
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  // 정액 할인 구현체
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {   // 주문 생성
        Member member = memberRepository.findById(memberId);        // 저장소 id를 찾아서 반환
        int discountPrice = discountPolicy.discount(member, itemPrice);  // 할인 정책

        return new Order(memberId, itemName, itemPrice, discountPrice); // Order 객체 반환
    }
}
