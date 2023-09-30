package hello.core.member;
import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientServiceTest {
    ClientService clientService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        clientService = appConfig.clientService();
    }

    @Test
    void join(){
        // given
        Client client = new Client(1L,"memberA", Grade.VIP);

        // when
        clientService.join(client);
        Client findClient = clientService.findMember(1L);

        //then
        Assertions.assertThat(client).isEqualTo(findClient);
        // junit에서 제공하는 메서드
        // assertThat은 member에 대한 객체를 검사하고 isEqualTo를 사용해서 객체 간 동등 비교를한다.
    }
}
