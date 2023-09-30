package hello.core.discount;

import hello.core.member.Client;
import hello.core.member.Grade;

public class FixDiscountPolicy implements DiscountPolicy{   // 정액 할인 정책 구현체

    private  int discountFixAmount = 1000;  // 1000원 할인
    @Override
    public int discount(Client client, int price) {
        if (client.getGrade() == Grade.VIP) return discountFixAmount;   // 회원 정보에서 등급이 VIP면 할인 반환
        else return 0;
    }
}
