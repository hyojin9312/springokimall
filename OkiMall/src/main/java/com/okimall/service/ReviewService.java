package com.okimall.service;

import java.util.List;

import com.okimall.domain.ReviewVO;
import com.okimall.util.Criteria;

public interface ReviewService {
	
	// 상품후기 총 개수
	public int countReview(int gds_no) throws Exception;
	
	// 상품후기 쓰기
	public void writeReview(ReviewVO vo, String mb_id) throws Exception;
	
	// 상품후기 리스트(페이지포함)
	public List<ReviewVO> listReview(int gds_no, Criteria cri) throws Exception;
	
	//상품후기 수정
	public void modifyReview(ReviewVO vo) throws Exception;
	
	// 상품후기 삭제
	public void deleteReview(int rv_no) throws Exception;

}
