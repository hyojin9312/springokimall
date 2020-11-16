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
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String memberAddOk(MemberVO vo, RedirectAttributes redirect) throws Exception{
		
		logger.info("입력데이터:" + vo.toString());
		logger.info("회원가입완료");
		
		//비밀번호 암호화 처리
		vo.setMb_pw(passEncrypt.encode(vo.getMb_pw()));
		
		service.join(vo);
		redirect.addFlashAttribute("msg", "JOIN_SUCCESS");
		
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
	
	// 비밀번호 재확인 
	@RequestMapping(value = "checkPw", method=RequestMethod.GET)
	public void checkPwGet(@ModelAttribute("url") String url) {
		
	}
	
	// checkPw 이후
	@RequestMapping(value = "checkPw", method = RequestMethod.POST)
	public String checkPwPost(@RequestParam("url") String url,	
							  @RequestParam("mb_pw") String pw, HttpSession session, Model model) throws Exception {
		
		logger.info("======checkPw() excute...");
		logger.info("======url:" + url + ",mb_pw:" + pw);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		if(passEncrypt.matches(pw, dto.getMb_pw())) {
			if(url.equals("modify")) {
				model.addAttribute("vo", service.readUser(dto.getMb_id()));
				return "/member/modify";
				
			} else if(url.equals("changePw")) {
				return "/member/changePw";
				
			} else if(url.equals("delete")) {
				return "/member/delete";
			}
		}
		
		// 비밀번호가 일치하지 않거나, url이 정해진 url이 아닌경우
		model.addAttribute("url", url);
		model.addAttribute("msg", "CHECK_PW_FAIL");
		return "/member/checkPw";
	}
	
	
	// 비밀번호 확인 ajax
	@ResponseBody
	@RequestMapping("checkPwAjax")
	public ResponseEntity<String> checkPwAjax(@RequestParam("mb_pw") String mb_pw, HttpSession session){
		
		logger.info("======checkPwAjax() excute...");
		ResponseEntity<String> entity = null;
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		logger.info("====mb_pw:" + mb_pw);
		logger.info("====dto:" + dto.toString());
		
		if(passEncrypt.matches(mb_pw, dto.getMb_pw())) {
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
		}
		
		return entity;
		
	}
	
	// 회원수정post
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyPost(MemberVO vo, RedirectAttributes redirect, HttpSession session) throws Exception {
		
		logger.info("수정데이터:" + vo.toString());
		
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
	
	// 비밀번호 변경
	@RequestMapping(value = "changePw", method = RequestMethod.POST)
	public String changePwPost(MemberDTO dto, RedirectAttributes redirect, HttpSession session) throws Exception {
		
		logger.info("==== changePwPost() excute....");

		// 비밀번호 암호화 후 변경
		dto.setMb_pw(passEncrypt.encode(dto.getMb_pw()));
		service.changePw(dto);
		
		// 세션 비밀번호 재설정
		MemberDTO memDTO = (MemberDTO) session.getAttribute("user");
		memDTO.setMb_pw(dto.getMb_pw());
		session.setAttribute("user", memDTO);
		
		redirect.addFlashAttribute("msg", "CHANGE_PW_SUCCESS");
		return "redirect:/";
	}
	
	// 회원탈퇴
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deletePost(String mb_id, HttpSession session, RedirectAttributes redirect) throws Exception {
		
		logger.info("======deletePost() excute....");
		
	    service.deleteUser(mb_id);
	    
	    // 회원탈퇴시 세션소멸작업
	    session.invalidate();
	    redirect.addFlashAttribute("msg", "DELETE_USER_SUCCESS");
		
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
