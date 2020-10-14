package com.okimall.service;

import com.okimall.dto.EmailDTO;

public interface EmailService {
	
	public void sendMail(EmailDTO dto, String authcode);

}
