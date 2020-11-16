package com.okimall.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
	private int totalCount;
	
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	// 페이지 번호의 개수
	private int displayPageNum = 5;
	
	private Criteria cri;
	

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}

	private void calcData() {
		// 현재 페이지에서의 종료페이지
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		
		// 현재페이지에서의 시작페이지
		startPage = (endPage - displayPageNum) + 1;
		
		// 전체종료페이지
		int tempEndPage = (int) Math.ceil(totalCount / (double)cri.getPerPageNum());
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		// 이전 다음 표시
		prev = startPage == 1? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount? false : true;
	}
	

	public int getTotalCount() {
		return totalCount;
	}


	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public String makeQuery(int page) {
		
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		
		return uriComponents.toUriString();
		
	}
	
	public String makeSearch(int page) {
		
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
				.queryParam("keyword", ((SearchCriteria)cri).getKeyword())
				.build();
		
		return uriComponents.toUriString();
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
	

}
