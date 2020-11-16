package com.okimall.util;

public class Criteria {
	
	private int page; // 현재 페이지
	private int perPageNum;  //페이지당 글의 게시물 개수
	
	//페이징
	private int rowStart; // 행시작
	private int rowEnd; //행 끝
	
	public Criteria() {
		
		this.page = 1;
		this.perPageNum = 5; 
	}

	public void setPage(int page) {
		
		if (page <= 0) 
		{
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		
		if (perPageNum <= 0 || perPageNum > 100) 
		{
			this.perPageNum = 5;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPage(){
		return page;
	}
	
	public int getPageStart() {
		return(this.page - 1) * perPageNum;
	}

	public int getPerPageNum() 
	{
		return this.perPageNum;
	}

	public int getRowStart() 
	{
		return ((page - 1) * perPageNum)+1;
	}

	public int getRowEnd() {
		return getRowStart() + perPageNum - 1;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", getRowStart()=" + getRowStart()
				+ ", getRowEnd()=" + getRowEnd() + "]";
	}

	
}
