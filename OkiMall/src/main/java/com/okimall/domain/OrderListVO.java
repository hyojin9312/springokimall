package com.okimall.domain;

import java.util.Date;

public class OrderListVO {
	
	private String gds_imag;
	private String gds_name;
	private String mb_id;
	private String ord_addr;
	private int ord_no;
	private int gds_no;
	private int ord_coun;
	private int ord_price;
	private int ord_disc;
	private int ord_total;
	private Date ord_date;
	
	//getter, setter
	public String getGds_imag() {
		return gds_imag;
	}
	public void setGds_imag(String gds_imag) {
		this.gds_imag = gds_imag;
	}
	public String getGds_name() {
		return gds_name;
	}
	public void setGds_name(String gds_name) {
		this.gds_name = gds_name;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String ord_addr() {
		return mb_id;
	}
	public void ord_addr(String ord_addr) {
		this.ord_addr = ord_addr;
	}
	public int getOrd_no() {
		return ord_no;
	}
	public void setOrd_no(int ord_no) {
		this.ord_no = ord_no;
	}
	public int getGds_no() {
		return gds_no;
	}
	public void setGds_no(int gds_no) {
		this.gds_no = gds_no;
	}
	public int getOrd_coun() {
		return ord_coun;
	}
	public void setOrd_coun(int ord_coun) {
		this.ord_coun = ord_coun;
	}
	public int getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}
	public int getOrd_disc() {
		return ord_disc;
	}
	public void setOrd_disc(int ord_disc) {
		this.ord_disc = ord_disc;
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
	
	//toString
	@Override
	public String toString() {
		return "OrderListVO [gds_imag=" + gds_imag + ", gds_name=" + gds_name + ", mb_id=" + mb_id +",ord_addr"+ord_addr + ", ord_no="
				+ ord_no + ", gds_no=" + gds_no + ", ord_coun=" + ord_coun + ", ord_price=" + ord_price + ", ord_disc="
				+ ord_disc + ", ord_total=" + ord_total + ", ord_date=" + ord_date + "]";
	}
	


}
