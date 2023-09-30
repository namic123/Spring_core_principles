package hello.core.xml;

import hello.core.member.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {
    @Test
    void xmlAppContext(){
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        ClientService clientService = ac.getBean("memberService", ClientService.class);
        assertThat(clientService).isInstanceOf(ClientService.class);
    }
}
