package com.okimall.domain;

import java.util.Date;

public class BoardVO {
	
	private int brd_no;
	private String mb_id;
	private String brd_title;
	private String brd_conte;
	private Date brd_date;
	
	// getter, setter
	public int getBrd_no() {
		return brd_no;
	}
	public void setBrd_no(int brd_no) {
		this.brd_no = brd_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getBrd_title() {
		return brd_title;
	}
	public void setBrd_title(String brd_title) {
		this.brd_title = brd_title;
	}
	public String getBrd_conte() {
		return brd_conte;
	}
	public void setBrd_conte(String brd_conte) {
		this.brd_conte = brd_conte;
	}
	public Date getBrd_date() {
		return brd_date;
	}
	public void setBrd_date(Date brd_date) {
		this.brd_date = brd_date;
	}
	
	//toString
	@Override
	public String toString() {
		return "BoardVO [brd_no=" + brd_no + ", mb_id=" + mb_id + ", brd_title=" + brd_title + ", brd_conte="
				+ brd_conte + ", brd_date=" + brd_date + "]";
	}	
	
}
