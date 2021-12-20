package com.jpabook.jpashop.service;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repositroy.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	void joinTest() throws Exception {
		//given
		Member member = new Member();
		member.setName("userA");
		
		//when
		Long join = memberService.join(member);
		
		//then
		assertEquals(member , memberRepository.findOne(join)) ;
	}
	
	@Test
	void validateDuplicateTest() throws Exception  {
		//given
		Member member1 = new Member();
		member1.setName("userA");
		
		Member member2 = new Member();
		member2.setName("userA");

		//when
		memberService.join(member1);
			
		//then
		IllegalStateException throws1 = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
		assertEquals("이미 존재하는 회원입니다", throws1.getMessage());
	}
}
