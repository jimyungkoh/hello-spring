package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * 단위 테스트가 좋은 테스트일 확률이 높다.
 * (상대적으로, 통합 테스트보다)
 */

class MemberServiceTest {
    
    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    /**
     * 테스트 코드는 과감하게 한글로 쓰셔도 됩니다!
     * 빌드될 때 테스트 코드는 실제 코드에 포함되지도 않아요.
     */
    @Test
    void 회원가입(){
        //given
        Member member = new Member();
        member.setName("hello");
        
        //when
        Long saveId = memberService.join(member);
       
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()
                                                -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.!");
 /**    
        try{
            memberService.join(member2);
            fail("");
        } catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.!");
        }
*/
        //then

    }

    @Test
    void findMembers(){

    }

    @Test
    void findOne(){

    }
}
