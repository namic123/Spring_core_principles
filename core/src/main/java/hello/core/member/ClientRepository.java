package hello.core.member;

// 회원 저장소 인터페이스
public interface ClientRepository {
    void save(Client client);   // 저장
    Client findById(Long memberId); // 회원 검색
}
