package com.okimall.domain;

public class CategoryVO {
	
	private String cate_code;
	private String cate_par;
	private String cate_name;
	
	// getter, setter
	public String getCate_code() {
		return cate_code;
	}
	public void setCate_code(String cate_code) {
		this.cate_code = cate_code;
	}
	public String getCate_par() {
		return cate_par;
	}
	public void setCate_par(String cate_par) {
		this.cate_par = cate_par;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	
	//toString
	@Override
	public String toString() {
		return "CategoryVO [cate_code=" + cate_code + ", cate_par=" + cate_par + ", cate_name=" + cate_name + "]";
	}
	
}
