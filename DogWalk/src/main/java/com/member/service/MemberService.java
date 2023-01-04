package com.member.service;

import com.member.model.MemberVO;

public interface MemberService {
	
	int MemberJoin(MemberVO member);
	
	int idCheck(String userid);
	
	int nickCheck(String nick);
	
	/* 로그인 */
    public MemberVO memberLogin(MemberVO member) throws Exception;
    
    public int updateMember(MemberVO member) throws Exception;

}
