package hello.core;

import hello.core.member.ClientService;
import hello.core.member.Grade;
import hello.core.member.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        ClientService clientService = appConfig.clientService();

        // 스프링 컨테이너, AppConfig에 @Bean이 붙은 멤버를 다 ApplicationContext에 넣어서 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ClientService clientService = applicationContext.getBean("clientService", ClientService.class);

        Client client = new Client(1L, "memberA", Grade.VIP);
        clientService.join(client);

        Client findClient = clientService.findMember(1L);
        System.out.println("new member = " + client.getName());
        System.out.println("findMember = " + findClient.getName());

    }
}
