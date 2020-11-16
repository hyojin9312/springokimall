package com.okimall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okimall.domain.ReviewVO;
import com.okimall.dto.MemberDTO;
import com.okimall.service.ReviewService;
import com.okimall.util.Criteria;
import com.okimall.util.PageMaker;

@Controller
@RequestMapping(value="/review/*")
public class ReviewController {

	@Inject
	private ReviewService service;
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	// 상품후기 쓰기
	@ResponseBody
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public void write(ReviewVO vo, HttpSession session) throws Exception{
		
		logger.info("======writepost execute....");
		logger.info("======vo:" + vo.toString());
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		
		service.writeReview(vo, dto.getMb_id());
		
	}
	
	// 상품후기 수정
	@ResponseBody
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public ResponseEntity<String> modify(ReviewVO vo){
		
		logger.info("======modify execute....");
		logger.info("======vo:" + vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			service.modifyReview(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		
		return entity;
		
	}
	
	// 상품후기 삭제
	@ResponseBody
	@RequestMapping(value = "{rv_no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rv_no") int rv_no){
		
		logger.info("======delete execute....");
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteReview(rv_no);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		
		return entity;
		
	}
	
	//상품후기 리스트(페이지포함)
	@ResponseBody
	@RequestMapping(value = "{gds_no}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listReview(
							@PathVariable("gds_no") Integer gds_no,
							@PathVariable("page") Integer page) {
		
		logger.info("======listReview execute....");
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			//페이지정보
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			//상품후기 리스트VO
			List<ReviewVO> list = service.listReview(gds_no, cri);
			logger.info("======ReviewVO"+ list.toString());
			
			map.put("list", list);
			
			int replyCount = service.countReview(gds_no);
			
			pageMaker.setTotalCount(replyCount);
			//하단페이지작업추가
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
