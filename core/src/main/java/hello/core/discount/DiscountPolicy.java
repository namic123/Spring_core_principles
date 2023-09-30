package hello.core.discount;

import hello.core.member.Client;

public interface DiscountPolicy {

    // return 할인 대상 금액
    int discount(Client client, int price);
}
