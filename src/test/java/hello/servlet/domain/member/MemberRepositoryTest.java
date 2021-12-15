package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {


    MemberRepository memberRepository = MemberRepository.getInstance();

    @BeforeEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("taewoo",20);
        //when
        Member saveMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findAll() {
        Member member1 = new Member("member1" , 20);
        Member member2 = new Member("member2" , 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> allMember = memberRepository.findAll();

        assertThat(allMember.size()).isEqualTo(2);
        assertThat(allMember).contains(member1,member2);
    }
}
