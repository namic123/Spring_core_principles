package hello.core.member;

// 회원 서비스 인터페이스
public interface ClientService {
    void join(Client client);   // 회원 가입
    Client findMember(Long memberId);   // 회원 조회
}
