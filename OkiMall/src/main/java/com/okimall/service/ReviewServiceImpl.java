package com.okimall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okimall.domain.ReviewVO;
import com.okimall.persistence.ReviewDAO;
import com.okimall.util.Criteria;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewDAO dao;
	
	//상품후기 총개수
	@Override
	public int countReview(int gds_no) throws Exception {
		
		return dao.countReview(gds_no);
		
	}
	
	// 상품후기 쓰기
	@Override
	public void writeReview(ReviewVO vo, String mb_id) throws Exception {
		vo.setMb_id(mb_id);
		dao.writeReview(vo);
		
	}
	
	// 상품후기 리스트(페이지포함)
	@Override
	public List<ReviewVO> listReview(int gds_no, Criteria cri) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("gds_no", gds_no);
		map.put("cri", cri);	
		
		return dao.listReview(map);
	
	}
	
	// 상품후기 수정
	@Override
	public void modifyReview(ReviewVO vo) throws Exception {
		
		dao.modifyReview(vo);
		
	}
	
	// 상품후기 삭제
	@Override
	public void deleteReview(int rv_no) throws Exception {
		
		dao.deleteReview(rv_no);
		
	}	
	
}
