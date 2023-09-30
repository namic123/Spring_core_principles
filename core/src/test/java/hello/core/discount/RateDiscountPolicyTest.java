package hello.core.discount;

import hello.core.member.Client;
import hello.core.member.Grade;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 성공 테스트
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        // given
        Client client = new Client(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(client, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x(){
        // given
        Client client = new Client(1L, "memberBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(client, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}