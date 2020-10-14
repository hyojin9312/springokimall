package com.okimall.dto;

import java.util.Date;

//아이디와 비번 입력정보를 저장용도
public class MemberDTO {
	
	private String mb_id;
	private String mb_pw;
	private String mb_name;
	private String mb_nick;
	private String mb_point;
	private Date mb_lasttime;
	
	// getter, setter
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_pw() {
		return mb_pw;
	}
	public void setMb_pw(String mb_pw) {
		this.mb_pw = mb_pw;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_nick() {
		return mb_nick;
	}
	public void setMb_nick(String mb_nick) {
		this.mb_nick = mb_nick;
	}
	public String getMb_point() {
		return mb_point;
	}
	public void setMb_point(String mb_point) {
		this.mb_point = mb_point;
	}
	public Date getMb_lasttime() {
		return mb_lasttime;
	}
	public void setMb_lasttime(Date mb_lasttime) {
		this.mb_lasttime = mb_lasttime;
	}
	
	
	
	

}
