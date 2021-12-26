package com.jpabook.jpashop.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpabook.jpashop.api.MemberApiController.MemberDTO;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	private final MemberService memberService;
	
	
	@GetMapping("/api/v1/members")
	public List<Member> membersV1(){
		return memberService.findMembers();
	}
	
	@GetMapping("/api/v2/members")
	public Result<List<MemberDTO>> membersV2() {
		List<Member> findMembers = memberService.findMembers();
		List<MemberDTO> memberDTO = findMembers.stream() 
										.map(m-> new MemberDTO(m.getName()))
										.collect(Collectors.toList());
		return new Result<>(memberDTO.size() ,memberDTO);
	}
	
	@Data
	@AllArgsConstructor
	static class Result<T>{
		private int count;
		private T data;
	}
	
	@Data
	@AllArgsConstructor
	static class MemberDTO{
		private String name;
	}
	
	@PostMapping("/api/v1/members")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
		Long uid = memberService.join(member);
		return new CreateMemberResponse(uid);
	}
	
	@PostMapping("/api/v2/members")
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
		Member member = new Member();
		member.setName(request.getName());
		Long uid = memberService.join(member);
		return new CreateMemberResponse(uid);	
	}
	
	@PutMapping("/api/v2/members/{id}")
	public UpdateMemberResponse updateMemberV2(
			@PathVariable("id") Long id, 
			@RequestBody @Valid UpdateMemberRequest request) {
		Long nid = memberService.update(id, request.getName());
		Member member = memberService.findOne(nid);
		return new UpdateMemberResponse(member.getId(), member.getName());
	}
	
	
	
	@Data
	static class UpdateMemberRequest{
		private String name;
	}
	
	@Data
	@AllArgsConstructor
	static class UpdateMemberResponse{
		private Long id;
		private String name;
	}
	
	@Data
	static class CreateMemberRequest{
		@NotEmpty
		private String name;	
	}
	
	@Data
	static class CreateMemberResponse{
	
		private Long id;
		
		public CreateMemberResponse(Long id) {
			this.id = id;	
		}
	}
}
