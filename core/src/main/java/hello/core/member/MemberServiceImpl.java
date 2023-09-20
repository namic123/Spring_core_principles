package hello.core.member;

// 회원 서비스 구현체
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {   // 회원 가입, Member 객체가 저장됨.
        memberRepository.save(member);  // Member 객체가 저장
    }

    @Override
    public Member findMember(Long memberId) {   // Long 타입 Member의 인스턴스 변수 id가 저장
        return memberRepository.findById(memberId);     //
    }
}
