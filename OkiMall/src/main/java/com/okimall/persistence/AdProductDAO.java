package com.okimall.persistence;

import java.util.List;
import java.util.Map;

import com.okimall.domain.CategoryVO;
import com.okimall.domain.ProductVO;
import com.okimall.util.SearchCriteria;

public interface AdProductDAO {
	
	// 1차카테고리
	public List<CategoryVO> mainCGList() throws Exception;
	
	// 1차카테고리에 따른 2차카테고리
	public List<CategoryVO> subCGList(String cate_code) throws Exception;
	
	// 상품등록
	public void insertProduct(ProductVO vo) throws Exception;
	
	// 상품리스트
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception;
	
	// 검색조건에 해당하는 상품개수
	public int searchListCount(SearchCriteria cri) throws Exception;
	
	// 상품정보
	public ProductVO readProduct(int gds_no) throws Exception;
	
	// 상품수정
	public void editProduct(ProductVO vo) throws Exception;
	
	// 상품삭제
	public void deleteProduct(int gds_no) throws Exception;
	
	// 선택한 상품수정
	public void editChecked(Map<String, Object> map) throws Exception;
}
