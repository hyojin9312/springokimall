package com.okimall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {
	
	private int gds_no;
	private String cate_code;
	private String cate_par;
	private String gds_name;
	private int gds_pric;
	private int gds_disc;
	private String gds_make;
	private String gds_deta;
	private String gds_imag;
	private int gds_odd;
	private String gds_buytf;
	private Date gds_redate;
	private Date gds_update;
	
	// 업로드 파일
	private MultipartFile file1;
	
	// getter, setter
	public int getGds_no() {
		return gds_no;
	}

	public void setGds_no(int gds_no) {
		this.gds_no = gds_no;
	}

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

	public String getGds_make() {
		return gds_make;
	}

	public void setGds_make(String gds_make) {
		this.gds_make = gds_make;
	}

	public String getGds_deta() {
		return gds_deta;
	}

	public void setGds_deta(String gds_deta) {
		this.gds_deta = gds_deta;
	}

	public String getGds_imag() {
		return gds_imag;
	}

	public void setGds_imag(String gds_imag) {
		this.gds_imag = gds_imag;
	}

	public int getGds_odd() {
		return gds_odd;
	}

	public void setGds_odd(int gds_odd) {
		this.gds_odd = gds_odd;
	}

	public String getGds_buytf() {
		return gds_buytf;
	}

	public void setGds_buytf(String gds_buytf) {
		this.gds_buytf = gds_buytf;
	}

	public Date getGds_redate() {
		return gds_redate;
	}

	public void setGds_redate(Date gds_redate) {
		this.gds_redate = gds_redate;
	}

	public Date getGds_update() {
		return gds_update;
	}

	public void setGds_update(Date gds_update) {
		this.gds_update = gds_update;
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	
	// toString
	@Override
	public String toString() {
		return "ProductVO [gds_no=" + gds_no + ", cate_code=" + cate_code + ", cate_par=" + cate_par + ", gds_name="
				+ gds_name + ", gds_pric=" + gds_pric + ", gds_disc=" + gds_disc + ", gds_make=" + gds_make
				+ ", gds_deta=" + gds_deta + ", gds_imag=" + gds_imag + ", gds_odd=" + gds_odd + ", gds_buytf="
				+ gds_buytf + ", gds_redate=" + gds_redate + ", gds_update=" + gds_update + ", file1=" + file1 + "]";
	}
	
	
	
	
}
