package com.member.service;

import java.util.List;

import com.family.pet.model.PetVO;
import com.member.model.MemberVO;

public interface MemberService {
	
	int MemberJoin(MemberVO member);
	
	int idCheck(String userid);
	
	int nickCheck(String nick);
	
	/* 로그인 */
    public MemberVO memberLogin(MemberVO member) throws Exception;
    
    public int updateMember(MemberVO member) throws Exception;
    public int removeMember(String userid);
    
    MemberVO selectById(String userid);
    
    //하현수 추가
    public List<PetVO> getPetInfo(String userid);
    
    
    public MemberVO normalLogin(String userid);

	Object adminUserList();

	List<MemberVO> adminUserList2();
	
	void dropUser(String userid);

	MemberVO userDetail(String user_id);

	//좋아요 눌렀는지 안눌렀는지 체크
	int findLike(String user_id);

	int getLike(String user_id);

	void likeDown(String user_no);

	int likeUp(String user_no);
}
