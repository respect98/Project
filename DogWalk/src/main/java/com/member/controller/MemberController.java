package com.member.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.member.model.MemberVO;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberService memberservice;

	@Autowired
	private JavaMailSender mailSender;

	// 회원가입 페이지 이동
	@GetMapping("/join")
	public void joinForm() {

		log.info("회원가입 페이지 진입");

	}

	// 회원가입
	@PostMapping("/join")
	public String joinPost(MemberVO member) {
		log.info("join 진입");

		// 회원가입 서비스 실행
		memberservice.MemberJoin(member);
		log.info("join Service 성공");

		return "redirect:/main";
	}

	// 로그인 페이지 이동
	@GetMapping("/login")
	public void loginForm() {

		log.info("로그인 페이지 진입");

	}

	// 아이디 중복 검사
	@PostMapping("/memberIdCheck")
	@ResponseBody
	public String memberIdCheckPOST(String userid) throws Exception {

		// log.info("memberIdCheck() 진입");

		int result = memberservice.idCheck(userid);

		// log.info("결과값 = "+result);

		if (result != 0) {
			return "fail"; // 중복된 아이디 존재
		} else {
			return "success"; // 중복된 아이디 x
		}

	}// -------------------------------------

	// 닉네임 중복 검사
	@PostMapping("/nickCheck")
	@ResponseBody
	public String nickCheckPOST(String nick) throws Exception {

		// log.info("nickCheck() 진입");

		int result = memberservice.nickCheck(nick);

		log.info("결과값 = " + result);

		if (result != 0) {
			return "fail";
		} else {
			return "success";
		}

	}// ---------------------------------------------

	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {

		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		log.info("인증번호 " + checkNum);

		/* 이메일 보내기 */
		String setFrom = "tmxhak12@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + "인증 번호는 " + checkNum + "입니다." + "<br>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String num = Integer.toString(checkNum);

		return num;

	}

	/* 로그인 */
	@PostMapping("/login")
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception {

		HttpSession session = request.getSession();
		MemberVO lvo = memberservice.memberLogin(member);

		if (lvo == null) { // 일치하지 않는 아이디, 비밀번호 입력 경우
			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect:/member/login";
		}
		session.setAttribute("member", lvo); // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
		return "redirect:/main";
		
	}
	
	 /* 메인페이지 로그아웃 */
	@GetMapping("logout.do")
    public String logoutMainGET(HttpServletRequest request) throws Exception{
		  	log.info("logoutMainGET메서드 진입");
	        
	        HttpSession session = request.getSession();
	        session.invalidate();
	        
	        return "redirect:/main";   
    }
 

}
// end---------------------------------------------------------------------------------
