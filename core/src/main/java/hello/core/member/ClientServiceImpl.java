package hello.core.member;

// 회원 서비스 구현체
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
