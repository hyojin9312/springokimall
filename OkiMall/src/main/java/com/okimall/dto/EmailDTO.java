package com.okimall.dto;

public class EmailDTO {
	
	private String senderName;
	private String senderMail;
	private String receiveMail;
	private String subject;
	private String message;
	
	//필드 기본값
	public EmailDTO() {
		this.senderName = "관리자";
		this.senderMail = "admin@oki.com";
		this.subject = "회원가입을 위한 인증코드";
		this.message = "회원가입을 위한 인증코드를 확인하고, 진행해주세요 \n";

	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public String getReceiveMail() {
		return receiveMail;
	}

	public void setReceiveMail(String receiveMail) {
		this.receiveMail = receiveMail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	// toString()
	@Override
	public String toString() {
		return "EmailDTO [senderName=" + senderName + ", senderMail=" + senderMail + ", receiveMail=" + receiveMail
				+ ", subject=" + subject + ", message=" + message + "]";
	}
	
	
	
	

}
