package com.okimall.domain;

import java.util.Date;

public class AdminVO {
	
	private String admin_id;
	private String admin_pw;
	private String admin_name;
	private Date admin_date_last;
	
	// getter, setter
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pw() {
		return admin_pw;
	}
	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public Date getAdmin_date_last() {
		return admin_date_last;
	}
	public void setAdmin_date_last(Date admin_date_last) {
		this.admin_date_last = admin_date_last;
	}
	
	// toString()
	@Override
	public String toString() {
		return "AdminVO [admin_id=" + admin_id + ", admin_pw=" + admin_pw + ", admin_name=" + admin_name
				+ ", admin_date_last=" + admin_date_last + "]";
	}
	
	
}
