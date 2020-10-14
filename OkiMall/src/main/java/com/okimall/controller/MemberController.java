package com.okimall.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.okimall.domain.MemberVO;
import com.okimall.dto.MemberDTO;
import com.okimall.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passEncrypt;
	
	// 회원가입
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join(){
		
		return "/member/join";
	}
	
	// 회원가입전송
	@RequestMapping(value = "joinOk", method = RequestMethod.POST)
	public String memberAddOk(MemberVO vo, RedirectAttributes redirect) throws Exception{
		
		logger.info("입력데이터:" + vo.toString());
		logger.info("회원가입완료");
		
		//비밀번호 암호화 처리
		vo.setMb_pw(passEncrypt.encode(vo.getMb_pw()));
		
		service.join(vo);
		
		return "redirect:/";
	}
	
	// 아이디중복체크
	@ResponseBody
	@RequestMapping(value = "idcheck", method = RequestMethod.POST)
	public ResponseEntity<String> idcheck(@RequestParam("mb_id") String mb_id) throws Exception{
		
		logger.info("======id check");
		ResponseEntity<String> entity = null;
		try {
			int count = service.idcheck(mb_id);
			
			//count가 0이면 아이디사용가능
			if(count != 0) {
				// 아이디 존재해서 사용불가능
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}else {
				//사용가능한 아이디
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 이메일인증코드 확인
	@ResponseBody
	@RequestMapping(value = "checkAutocode", method = RequestMethod.POST)
	public ResponseEntity<String> checkAutocode(@RequestParam("code") String code, HttpSession session) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		try {
			if(code.equals(session.getAttribute("authcode"))) {
				//인증코드 일치
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				//인증코드 불일치
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		
		return entity;
		
	}
	
	// 비밀번호 확인 
	@RequestMapping(value = "checkPW", method=RequestMethod.GET)
	public void checkPw(@ModelAttribute("url") String url) {
		
	}
	
	// 회원정보 수정, 비밀번호 변경, 회원탈퇴
	public String checkPwpost() {
		
		return "";
	}
	
	
	// 회원수정post
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyPost(MemberVO vo, RedirectAttributes redirect, HttpSession session) throws Exception {
		
		MemberDTO dto = new MemberDTO();
		dto.setMb_id(vo.getMb_id());
		dto.setMb_pw(vo.getMb_pw());
		
		// 비밀번호 암호화작업
		vo.setMb_pw(passEncrypt.encode(vo.getMb_pw()));
		service.modifyUser(vo); // 회원수정
		
		// 세션작업
		session.setAttribute("user", service.login(dto));
		
		redirect.addFlashAttribute("msg", "MODIFY_USER_SUCCESS");
		
		return "redirect:/";
	}
	
	
	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(){
		
		return "/member/login";
	}
	
	// 로그인전송
	@RequestMapping(value = "loginOk", method = RequestMethod.POST)
	public String loginOk(MemberDTO dto, RedirectAttributes redirect, HttpSession session,
						Model model, HttpServletResponse response) throws Exception {
		
		logger.info("=======loginOk() excute...");
		
		// 암호화된 비번저장
		MemberDTO memDTO = service.login(dto);
		
		if(memDTO != null) { //로그인성공
			logger.info("====== 로그인성공");
			session.setAttribute("user", memDTO);
			
			redirect.addFlashAttribute("msg", "Login_success");
			return "redirect:/";
		}else {
			logger.info("====== 로그인실패");
			
			redirect.addFlashAttribute("msg", "LOGIN_FAIL");
			return "redirect:/member/login";
		}
		
		
	}
	
	// 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes redirect) throws Exception{
		
		logger.info("======logout excute()....");
		
		session.invalidate(); // 세션처리한 정보가 서버메모리에서 제거, 로그아웃 기능.
		redirect.addFlashAttribute("msg", "LOGOUT_SUCCESS");
		
		return "redirect:/";
	}
}
