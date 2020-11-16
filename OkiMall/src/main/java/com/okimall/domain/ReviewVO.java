package com.okimall.domain;

import java.util.Date;

public class ReviewVO {
	
	private int rv_no;
	private String mb_id;
	private int gds_no;
	private String rv_conte;
	private int rv_grade;
	private Date rv_date;
	
	
	// getter, setter
	public int getRv_no() {
		return rv_no;
	}
	public void setRv_no(int rv_no) {
		this.rv_no = rv_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getGds_no() {
		return gds_no;
	}
	public void setGds_no(int gds_no) {
		this.gds_no = gds_no;
	}
	public String getRv_conte() {
		return rv_conte;
	}
	public void setRv_conte(String rv_conte) {
		this.rv_conte = rv_conte;
	}
	public int getRv_grade() {
		return rv_grade;
	}
	public void setRv_grade(int rv_grade) {
		this.rv_grade = rv_grade;
	}
	public Date getRv_date() {
		return rv_date;
	}
	public void setRv_date(Date rv_date) {
		this.rv_date = rv_date;
	}
	
	// toString
	@Override
	public String toString() {
		return "ReviewVO [rv_no=" + rv_no + ", mb_id=" + mb_id + ", gds_no=" + gds_no + ", rv_conte=" + rv_conte
				+ ", rv_grade=" + rv_grade + ", rv_date=" + rv_date + "]";
	}
	
	
	
	
}
