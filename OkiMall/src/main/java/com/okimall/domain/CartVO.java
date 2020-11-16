package com.okimall.domain;

public class CartVO {
	
	private int cart_code;
	private int gds_no;
	private String mb_id;
	private int cart_amount;
	
	// getter, setter
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	public int getGds_no() {
		return gds_no;
	}
	public void setGds_no(int gds_no) {
		this.gds_no = gds_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getCart_amount() {
		return cart_amount;
	}
	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	
	//toString 
	@Override
	public String toString() {
		return "CartVO [cart_code=" + cart_code + ", gds_no=" + gds_no + ", mb_id=" + mb_id + ", cart_amount="
				+ cart_amount + "]";
	}
	
	

}
