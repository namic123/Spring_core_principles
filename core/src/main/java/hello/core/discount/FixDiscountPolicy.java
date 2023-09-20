package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{   // 정액 할인 정책 구현체

    private  int discountFixAmount = 1000;  // 1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) return discountFixAmount;   // 회원 정보에서 등급이 VIP면 할인 반환
        else return 0;
    }
}
