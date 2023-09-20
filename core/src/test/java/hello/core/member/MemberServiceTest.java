package hello.core.member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();
    @Test
    void join(){
        // given
        Member member = new Member(1L,"memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
        // junit에서 제공하는 메서드
        // assertThat은 member에 대한 객체를 검사하고 isEqualTo를 사용해서 객체 간 동등 비교를한다.
    }
}
