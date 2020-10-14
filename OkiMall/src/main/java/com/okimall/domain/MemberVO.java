package com.okimall.domain;

import java.util.Date;

public class MemberVO {
	
	private String mb_id;
	private String mb_pw;
	private String mb_name;
	private String mb_epost;
	private String mb_addr;
	private String mb_daddr;
	private String mb_nick;
	private String mb_email;
	private String mb_phone;
	private String mb_age;
	private String mb_authcode;
	private int mb_point;
	private Date mb_joindate;
	private Date  mb_revdate;
	private Date mb_lasttime;
		
	//getter, setter
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
	public String getMb_epost() {
		return mb_epost;
	}
	public void setMb_epost(String mb_epost) {
		this.mb_epost = mb_epost;
	}
	public String getMb_addr() {
		return mb_addr;
	}
	public void setMb_addr(String mb_addr) {
		this.mb_addr = mb_addr;
	}
	public String getMb_daddr() {
		return mb_daddr;
	}
	public void setMb_daddr(String mb_daddr) {
		this.mb_daddr = mb_daddr;
	}
	public String getMb_nick() {
		return mb_nick;
	}
	public void setMb_nick(String mb_nick) {
		this.mb_nick = mb_nick;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public String getMb_phone() {
		return mb_phone;
	}
	public void setMb_phone(String mb_phone) {
		this.mb_phone = mb_phone;
	}
	public String getMb_age() {
		return mb_age;
	}
	public void setMb_age(String mb_age) {
		this.mb_age = mb_age;
	}
	public String getMb_authcode() {
		return mb_authcode;
	}
	public void setMb_authcode(String mb_authcode) {
		this.mb_authcode = mb_authcode;
	}
	public int getMb_point() {
		return mb_point;
	}
	public void setMb_point(int mb_point) {
		this.mb_point = mb_point;
	}
	public Date getMb_joindate() {
		return mb_joindate;
	}
	public void setMb_joindate(Date mb_joindate) {
		this.mb_joindate = mb_joindate;
	}
	public Date getMb_revdate() {
		return mb_revdate;
	}
	public void setMb_revdate(Date mb_revdate) {
		this.mb_revdate = mb_revdate;
	}
	public Date getMb_lasttime() {
		return mb_lasttime;
	}
	public void setMb_lasttime(Date mb_lasttime) {
		this.mb_lasttime = mb_lasttime;
	}
	
	// toString()
	@Override
	public String toString() {
		return "MemberVO [mb_id=" + mb_id + ", mb_pw=" + mb_pw + ", mb_name=" + mb_name + ", mb_epost=" + mb_epost
				+ ", mb_addr=" + mb_addr + ", mb_daddr=" + mb_daddr + ", mb_nick=" + mb_nick + ", mb_email=" + mb_email
				+ ", mb_phone=" + mb_phone +",mb_age="+ mb_age + ", mb_authcode=" + mb_authcode + ", mb_point=" + mb_point
				+ ", mb_joindate=" + mb_joindate + ", mb_revdate=" + mb_revdate + ", mb_lasttime=" + mb_lasttime + "]";
	}
	
	
}
