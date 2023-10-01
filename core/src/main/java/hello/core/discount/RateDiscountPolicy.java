package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Client;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;
    @Override
    public int discount(Client client, int price) {
        if(client.getGrade() == Grade.VIP) return price * discountPercent / 100;
        else return 0;
    }
}
