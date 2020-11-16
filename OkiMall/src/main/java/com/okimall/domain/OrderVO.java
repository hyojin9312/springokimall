package com.okimall.domain;

import java.util.Date;

public class OrderVO {
	
	private int ord_no;
	private String mb_id;
	private String ord_name;
	private String ord_epost;
	private String ord_addr;
	private String ord_daddr;
	private String ord_phone;
	private int ord_total;
	private Date ord_date;
	
	//getter, setter
	public int getOrd_no() {
		return ord_no;
	}
	public void setOrd_no(int ord_no) {
		this.ord_no = ord_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getOrd_name() {
		return ord_name;
	}
	public void setOrd_name(String ord_name) {
		this.ord_name = ord_name;
	}
	public String getOrd_epost() {
		return ord_epost;
	}
	public void setOrd_epost(String ord_epost) {
		this.ord_epost = ord_epost;
	}
	public String getOrd_addr() {
		return ord_addr;
	}
	public void setOrd_addr(String ord_addr) {
		this.ord_addr = ord_addr;
	}
	public String getOrd_daddr() {
		return ord_daddr;
	}
	public void setOrd_daddr(String ord_daddr) {
		this.ord_daddr = ord_daddr;
	}
	public String getOrd_phone() {
		return ord_phone;
	}
	public void setOrd_phone(String ord_phone) {
		this.ord_phone = ord_phone;
	}
	public int getOrd_total() {
		return ord_total;
	}
	public void setOrd_total(int ord_total) {
		this.ord_total = ord_total;
	}
	public Date getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}
	
	// toString
	@Override
	public String toString() {
		return "OrderVO [ord_no=" + ord_no + ", mb_id=" + mb_id + ", ord_name=" + ord_name + ", ord_epost=" + ord_epost
				+ ", ord_addr=" + ord_addr + ", ord_daddr=" + ord_daddr + ", ord_phone=" + ord_phone + ", ord_total="
				+ ord_total + ", ord_date=" + ord_date + "]";
	}
	
}
