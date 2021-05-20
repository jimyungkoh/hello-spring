package com.example.hellospring;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.example.hellospring.aop.TimeTraceAop;
import com.example.hellospring.repository.JdbcMemberRepository;
import com.example.hellospring.repository.JdbcTemplateMemberRepository;
import com.example.hellospring.repository.JpaMemberRepository;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import com.example.hellospring.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /**
     * private EntityManager em;
     * 
     * @Autowired public SpringConfig(EntityManager em){ this.em=em; }s private
     *            DataSource dataSource;
     * 
     * @Autowired public SpringConfig(DataSource dataSource) { this.dataSource =
     *            dataSource; }
     */
    /**
     * @Bean public MemberRepository memberRepository() { //return new
     *       JdbcTemplateMemberRepository(dataSource); return new
     *       JpaMemberRepository(em); }
     */
}
