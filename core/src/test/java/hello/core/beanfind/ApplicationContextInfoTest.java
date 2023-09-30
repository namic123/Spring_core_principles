package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("빈의 이름 =" + beanDefinitionName + "//// 빈의 객체 = "+ bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);// 빈 각각에 대한 메타 정보

            // getRole()은 빈의 정의 된 여러가지 역할 중 어떤 역할인지를 출력
            // ROLE_APPLICATION은 스프링 내부가 아닌 애프리케인션 개발을 위해 등록한 빈을 의미
            // 위 테스트 케이스와는 다르게 기본 빈을 제외하고 애플리케이션 개발을 위해 등록된 빈만 출력

            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);// 어떤 타입이 올지 모르니 Object 타입으로 가져옴
                System.out.println(" = " + beanDefinitionName +" Object = "+bean);
        }
        }
    }
}
