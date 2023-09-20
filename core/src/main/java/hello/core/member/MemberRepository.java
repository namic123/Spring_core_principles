package hello.core.member;

// 회원 저장소 인터페이스
public interface MemberRepository {
    void save(Member member);   // 저장
    Member findById(Long memberId); // 회원 검색
}
