package com.family.controller;

import java.security.*;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.family.pet.model.Mrecord;
import com.family.pet.service.MedicalService;
import com.family.pet.service.PetService;
import com.member.model.MemberVO;

@Controller
@RequestMapping("/management/")
public class MedicalController {
	
	private static final Logger logger = LoggerFactory.getLogger(MedicalController.class);
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private MedicalService medicalService;
	
	

	// 병원이용 홈 보여주기
	@RequestMapping(value = "getMrecordList.bit", method = RequestMethod.GET) 
	public String getMrecordList(String cp, String ps, Principal principal, Model model,HttpSession httpSession) {
		
		String userid = (String) httpSession.getAttribute("userid");
//		String userid =  principal.getName();
		logger.info("로그인 유저 아이디: "+userid);
		
		
		HashMap<String, Object> map = medicalService.getMrecordList(cp, ps, userid);		
		logger.info("병원이용 리스트 조회 완료");
		
		
		
		model.addAttribute("cpage", map.get("cpage"));
		model.addAttribute("pageSize", map.get("pageSize"));
		model.addAttribute("mrecordList", map.get("mrecordList"));
		model.addAttribute("pageCount", map.get("pageCount"));
		model.addAttribute("totalMrecordCount", map.get("totalMrecordCount"));
		logger.info("병원이용 정보 뷰로 쐈당");

		return "management/main";
		
		
	}

	// 병원이용기록 등록 페이지 보여주기/petname 가져오기
	@RequestMapping(value = "medicalRegister.bit", method = RequestMethod.GET)
	public String writeMedical(Principal principal, Model model,HttpSession httpSession) {
		
//		String userid =  principal.getName();
		String userid = (String) httpSession.getAttribute("userid");
		logger.info("로그인 유저 아이디: "+userid);
		
		HashMap<String, Object> map = medicalService.getPetname(userid);		
		logger.info("petname 가져오기 완료");	
		
		model.addAttribute("petNameList", map.get("petNameList"));
		model.addAttribute("totalMrecordCount", map.get("totalMrecordCount"));
		logger.info("petname이랑 userid 뷰로 쐈당");
		return "management/medicalRegister";
	}
	
	// 병원이용기록 등록 처리
	@RequestMapping(value = "medicalRegister.bit", method = RequestMethod.POST)
	public String writeMedical(Mrecord mrecord, HttpSession httpSession, Principal principal, RedirectAttributes redirectAttributes, Model model) {
		
		//////////////////////////파일 업로드 구현 빠진 상태////////////////////////////
		
		String userid = (String) httpSession.getAttribute("userid");
//		String userid =  principal.getName();
		logger.info("반려동물을 등록한 유저 아이디: " + userid);
		
		// 반려동물 등록한 유저 아이디 저장
		mrecord.setUserid(userid);
		MemberVO member = (MemberVO)httpSession.getAttribute("member");	
		int result = medicalService.medicalRegister(mrecord);
		logger.info("프론트에서 값 받아오기 성공");
		
		
		if(result == 1) {
			
			logger.info("병원 기록 등록 성공");

			return "redirect:/management/main.bit?tab=mrecord";
	        
		} else { 
			
			redirectAttributes.addFlashAttribute("failedRegisterPet", "failed");
			logger.info("병원 기록 등록 실패");
			
			return "javascript:history.back()";

		}
		
	}
	
		// 병원 이용기록 상세 페이지 view
		@RequestMapping(value = "getMrecordDetail.bit", method = RequestMethod.GET)
		public String getMrecordDetail(String mindex, Principal principal, Model model,HttpSession httpSession) {
			
			Mrecord mrecord = medicalService.getMrecordDetail(mindex);
			
			
			String userid = (String) httpSession.getAttribute("userid");
			HashMap<String, Object> map = medicalService.getPetname(userid);		
			logger.info("petname 가져오기 완료");
			
			
			model.addAttribute("mrecord", mrecord);
			model.addAttribute("petNameList", map.get("petNameList"));
			System.out.println("mrecord: "+ mrecord);
			return "management/medicalDetail";	
		}
		
		
		//  병원 이용 기록 수정 view
		@RequestMapping(value = "editMrecord.bit", method = RequestMethod.GET)
		public String editMrecord(String mindex, Principal principal, Model model,HttpSession httpSession) {
			
			Mrecord mrecord = medicalService.getMrecordDetail(mindex);			
			logger.info("병원이용기륵 조회 완료");
			
			//petname 가져오기... 쉬불..
//			String userid =  principal.getName();
			String userid = (String) httpSession.getAttribute("userid");
			HashMap<String, Object> map = medicalService.getPetname(userid);		
			logger.info("petname 가져오기 완료");
			
			model.addAttribute("mrecord", mrecord);
			model.addAttribute("petNameList", map.get("petNameList"));
			
			return "management/medicalUpdate";	
		}
		
		// 글 수정 처리
		@RequestMapping(value = "editMrecord.bit", method = RequestMethod.POST)
		public String editMrecord(Mrecord mrecord, Model model) {
												
			String msg = null;
			String url = null;
				
			int result = medicalService.editMrecord(mrecord);
		
			if(result==1) {
				
				logger.info("병원이용기륵 수정 완료");
//				msg = "병원이용기륵 수정 완료";
//		        url = "getMrecordDetail.bit?mindex="+mrecord.getMindex();
		        return "redirect:/management/main.bit?mindex="+mrecord.getMindex();
				
			}else { 
				
				logger.info("병원이용기륵 수정 실패");
				
		        return "redirect:/management/main.bit";

			}
			
			
		}
	
		// 마이페이지 > 글 삭제 처리
		@RequestMapping(value = "deleteMrecord.bit", method = {RequestMethod.GET, RequestMethod.POST})
		public String deleteMrecord(Mrecord mrecord, Model model) {
												
//			String msg = null;
//			String url = null;
				
			int result = medicalService.deleteMrecord(mrecord);
		
			if(result==1) {
				
				logger.info("병원방문 글 삭제 완료");
//				
				return "redirect:/management/main.bit";
				
			}else { 
				
				logger.info("병원방문 글 삭제 실패");
//				
		        return "redirect:/management/main.bit";
			}
		}
}
