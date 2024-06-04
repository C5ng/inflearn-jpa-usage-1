package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(false) 기본은 true
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("C5ng");

        //when
        Long saveId = memberService.join(member);

        //then
        em.flush(); // DB에 반영하게 된다.
        assertEquals(member, memberRepository.findOne(saveId)); // 같은 TX에서 같은 아이디(PK) 값이면 하나의 영속성에서 같이 관리된다.
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("C5ng");

        Member member2 = new Member();
        member2.setName("C5ng");

        //when
        memberService.join(member1);
//        memberService.join(member2); // 예외가 발생해야 한다.

        //then
        Assertions.assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)
        ); // memberService.join(member2)를 실행했을 때 IllegalStateException이 나오면 True
    }
}