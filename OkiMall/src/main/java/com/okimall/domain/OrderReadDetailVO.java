package com.okimall.domain;

public class OrderReadDetailVO {
	
	private int ord_coun;
	private int gds_no;
	private int ord_price;
	private int gds_pric;
	private int ord_disc;
	private String gds_name;
	private String gds_imag;
	
	// getter, setter
	public int getOrd_coun() {
		return ord_coun;
	}
	public void setOrd_coun(int ord_coun) {
		this.ord_coun = ord_coun;
	}
	public int getGds_no() {
		return gds_no;
	}
	public void setGds_no(int gds_no) {
		this.gds_no = gds_no;
	}
	public int getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}
	public int getGds_pric() {
		return gds_pric;
	}
	public void setGds_pric(int gds_pric) {
		this.gds_pric = gds_pric;
	}
	public int getOrd_disc() {
		return ord_disc;
	}
	public void setOrd_disc(int ord_disc) {
		this.ord_disc = ord_disc;
	}
	public String getGds_name() {
		return gds_name;
	}
	public void setGds_name(String gds_name) {
		this.gds_name = gds_name;
	}
	public String getGds_imag() {
		return gds_imag;
	}
	public void setGds_imag(String gds_imag) {
		this.gds_imag = gds_imag;
	}
	
	// toString
	@Override
	public String toString() {
		return "OrderReadDetailVO [ord_coun=" + ord_coun + ", gds_no=" + gds_no + ", ord_price=" + ord_price
				+ ", gds_pric=" + gds_pric + ", ord_disc=" + ord_disc + ", gds_name=" + gds_name + ", gds_imag="
				+ gds_imag + "]";
	}
	
	
	
	
}
