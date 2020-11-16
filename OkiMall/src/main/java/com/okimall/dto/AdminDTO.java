package com.okimall.dto;

import java.util.Date;

public class AdminDTO {
	
	private String admin_id;
	private String admin_pw;
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
	public Date getAdmin_date_last() {
		return admin_date_last;
	}
	public void setAdmin_date_last(Date admin_date_last) {
		this.admin_date_last = admin_date_last;
	}
	
	// toString
	@Override
	public String toString() {
		return "AdminDTO [admin_id=" + admin_id + ", admin_pw=" + admin_pw + ", admin_date_last=" + admin_date_last
				+ "]";
	}
	
	

}
