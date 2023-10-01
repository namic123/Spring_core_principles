package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Client;
import hello.core.member.Grade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

// 해당 타입의 모든 빈 찾기
public class AllBeanTest {

@Test
@DisplayName("타입의 모든 빈 찾기")
    void findAllBean(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
// AutoConfigApp도 등록한 이유는 아래 DiscountService는 본인만 스프링빈이기 때문에
// DiscountPolicy 타입 빈을 가져오려면 빈을 포함하고 있는 AutoAppConfig도 같이 빈으로 등록해준다.
DiscountService discountService = ac.getBean(DiscountService.class);
Client client = new Client(1L, "userA", Grade.VIP);
int discountPrice = discountService.discount(client, 10000, "fixDiscountPolicy");
    assertThat(discountService).isInstanceOf(DiscountService.class);
    assertThat(discountPrice).isEqualTo(1000);

    int rateDiscountPrice = discountService.discount(client,20000,"rateDiscountPolicy");
    assertThat(rateDiscountPrice).isEqualTo(2000);
}


// 테스트를 위한 빈
static class DiscountService{
    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policies;

    @Autowired
    public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
        this.policyMap = policyMap;
        this.policies = policies;
        System.out.println("policyMap = " + policyMap);
        System.out.println("policies = " + policies);
    }
    public int discount(Client client, int price, String discountCode){
        DiscountPolicy discountPolicy = policyMap.get(discountCode);
        return discountPolicy.discount(client, price);
    }
}
}
