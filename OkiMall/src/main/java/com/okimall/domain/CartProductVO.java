package com.okimall.domain;

public class CartProductVO {
	
	private int cart_code;
	private int cart_amount;
	private int gds_no;
	private String gds_name;
	private int gds_pric;
	private int gds_disc;
	private String gds_imag;
	
	// getter, setter
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	public int getCart_amount() {
		return cart_amount;
	}
	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	public int getGds_no() {
		return gds_no;
	}
	public void setGds_no(int gds_no) {
		this.gds_no = gds_no;
	}
	public String getGds_name() {
		return gds_name;
	}
	public void setGds_name(String gds_name) {
		this.gds_name = gds_name;
	}
	public int getGds_pric() {
		return gds_pric;
	}
	public void setGds_pric(int gds_pric) {
		this.gds_pric = gds_pric;
	}
	public int getGds_disc() {
		return gds_disc;
	}
	public void setGds_disc(int gds_disc) {
		this.gds_disc = gds_disc;
	}
	public String getGds_imag() {
		return gds_imag;
	}
	public void setGds_imag(String gds_imag) {
		this.gds_imag = gds_imag;
	}
	
	//toString
	@Override
	public String toString() {
		return "CartProductVO [cart_code=" + cart_code + ", cart_amount=" + cart_amount + ", gds_no=" + gds_no
				+ ", gds_name=" + gds_name + ", gds_pric=" + gds_pric + ", gds_disc=" + gds_disc + ", gds_imag="
				+ gds_imag + "]";
	}
	
}
