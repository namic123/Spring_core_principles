package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 회원 서비스 구현체
@Component
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // 테스트 용도로 작성
    public ClientRepository getClientRepository(){
        return clientRepository;
    }

    @Override
    public void join(Client client) {   // 회원 가입, Member 객체가 저장됨.
        clientRepository.save(client);  // Member 객체가 저장
    }

    @Override
    public Client findMember(Long memberId) {   // Long 타입 Member의 인스턴스 변수 id가 저장
        return clientRepository.findById(memberId);
    }
}
