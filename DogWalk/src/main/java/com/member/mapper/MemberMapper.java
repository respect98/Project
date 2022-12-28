package com.member.mapper;

import com.member.model.MemberVO;

public interface MemberMapper {
	 int MemberJoin(MemberVO member);//회원등록
	 
	 int idCheck(String userid);
	 
	 int nickCheck(String nick);
	 
	 MemberVO memberLogin(MemberVO member);//로그인
	 
}
