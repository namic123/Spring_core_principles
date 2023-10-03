package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeTestBean.class);

        PrototypeTestBean prototypeTestBean1 = ac.getBean(PrototypeTestBean.class);
        PrototypeTestBean prototypeTestBean2 = ac.getBean(PrototypeTestBean.class);

        System.out.println("prototypeTestBean1 = " + prototypeTestBean1);
        System.out.println("prototypeTestBean2 = " + prototypeTestBean2);
        assertThat(prototypeTestBean1).isNotSameAs(prototypeTestBean2);

        ac.close(); // prototype은 클라이언트에게 빈을 전달하고 그 이후에는 관리 안하기 때문에 호출되지 않음
    }

    @Scope("prototype")
    static class PrototypeTestBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeTestBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeTestBean.destroy");
        }
    }
}
