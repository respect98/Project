package com.member.mapper;

import java.util.List;

import com.member.model.MemberVO;

public interface MemberMapper {
	 int MemberJoin(MemberVO member);//회원등록
	 
	 int idCheck(String userid);
	 
	 int nickCheck(String nick);
	 
	 MemberVO memberLogin(MemberVO member);//로그인
	 
	 int updateMember(MemberVO member);
	 
	 int removeMember(String userid);
	 
	 MemberVO selectById(String userid);
	 
	 public List<String> getNick(String nick);	 
	 
	 
	 public MemberVO getUser(String userid);
	 //하현수 0112추가
	 public MemberVO normalLogin(String userid);

	List<MemberVO> adminUserList();

	List<MemberVO> adminUserList2();

	void successId(String id);

	void failed(String id);

	void dropUser(String id);

	//2023-01-17////////////////////////////////////
	MemberVO freeDetail(String user_id); //상세보기

	int likeUp(String user_no);
	 
	 
}
