package hello.core;

import hello.core.member.ClientRepository;
import hello.core.member.MemoryClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", // member 패키지의 하위 패키지만을 스캔
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)  // 자동으로 스프링 빈을 끌어올리는 에너테이션
public class AutoAppConfig {

    @Bean("memoryClientRepository") // 이미 AutoAppConfig에는 자동으로 등록된 빈이 있는데 예시를 위한 중복 수동 등록
    public ClientRepository clientRepository(){
        return new MemoryClientRepository();
    }
    // 예외가 발생하지 않음.
    // 수동으로 등록한 빈이 우선권을 가지며, 자동 빈을 오버라이딩함.
}
