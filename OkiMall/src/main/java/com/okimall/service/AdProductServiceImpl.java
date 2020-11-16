package com.okimall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okimall.domain.CategoryVO;
import com.okimall.domain.ProductVO;
import com.okimall.persistence.AdProductDAO;
import com.okimall.util.SearchCriteria;

@Service
public class AdProductServiceImpl implements AdProductService {
	
	@Autowired
	AdProductDAO dao;
	
	//1차카테고리
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
	
		return dao.mainCGList();
	
	}
	
	// 1차카테고리에 따른 2차카테고리
	@Override
	public List<CategoryVO> subCGList(String cate_code) throws Exception {
		
		return dao.subCGList(cate_code);
	}
	
	// 상품등록
	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		
		dao.insertProduct(vo);
		
	}
	
	// 상품리스트
	@Override
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception {
		
		return dao.searchListProduct(cri);
	}

	// 검색조건 상품개수
	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
		
		return dao.searchListCount(cri);
	}

	//상품정보
	@Override
	public ProductVO readProduct(int gds_no) throws Exception {
	
		return dao.readProduct(gds_no);
		
	}

	// 상품수정
	@Override
	public void editProduct(ProductVO vo) throws Exception {
		
		dao.editProduct(vo);
		
	}
	
	// 상품 삭제
	@Transactional
	@Override
	public void deleteProduct(int gds_no) throws Exception {
	
		dao.deleteProduct(gds_no);
		
	}
	
	//선택한 상품 수정
	@Override
	public void editChecked(Map<String, Object> map) throws Exception {
		
		dao.editChecked(map);
		
	}
	
	
	
}
