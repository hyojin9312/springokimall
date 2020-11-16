package com.okimall.domain;

public class OrderDetailVO {
	
	private int ord_no;
	private int gds_no;
	private int ord_coun;
	private int ord_price;
	private int ord_disc;
	
	// getter, setter   
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
	
	// toString
	@Override
	public String toString() {
		return "OrderDetailVO [ord_no=" + ord_no + ", gds_no=" + gds_no + ", ord_coun=" + ord_coun + ", ord_price="
				+ ord_price + ", ord_disc=" + ord_disc + "]";
	}

}
