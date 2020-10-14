package com.okimall.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okimall.dto.EmailDTO;
import com.okimall.service.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@Inject
	private EmailService emailService;
	
	@ResponseBody
	@RequestMapping("send")
	public ResponseEntity<String> send(EmailDTO dto, Model model, HttpSession session){
		
		logger.info("=====email send execute()....");
		logger.info("====EmailDTO:"+ dto.toString());
		
		ResponseEntity<String> entity = null;
		
		String authcode ="";
		for(int i=0; i<6; i++) {
			authcode += String.valueOf((int)(Math.random()*10));
		}
		
		// 인증코드를 세션에 저장
		session.setAttribute("authcode", authcode);
		logger.info("=====authcode:" + authcode);
		
		try {
			//dto: 회원가입시 입력한 메일주소, authcode: 인증코드
			emailService.sendMail(dto, authcode);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}

}
