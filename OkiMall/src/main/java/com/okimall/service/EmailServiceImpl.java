package com.okimall.service;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.okimall.dto.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Inject
	JavaMailSender mailSender;
	
	@Override
	public void sendMail(EmailDTO dto, String authcode) {
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			// 받는사람의 주소
			message.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			
			//보내는사람의 정보(이메일, 이름)
			message.addFrom(new InternetAddress[]
					{new InternetAddress(dto.getSenderMail(), dto.getSenderName())});
			
			// 제목
			message.setSubject(dto.getSubject(), "utf-8");
			
			// 내용
			message.setText(dto.getMessage() + authcode, "utf-8");
			
			// 메일보내기
			mailSender.send(message);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
