package com.example.hellospring.controller;

import java.util.List;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
        /**프록시를 통한 가짜 memberService(by proxy) 확인 출력
         * memberService = class com.example.hellospring.service.MemberService
         *                  $$EnhancerBySpringCGLIB$$29a74295
        */
        System.out.println("memberService = " + memberService.getClass());;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members= memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
