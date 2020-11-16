package com.okimall.persistence;

import java.util.List;
import java.util.Map;

import com.okimall.domain.CategoryVO;
import com.okimall.domain.ProductVO;

public interface ProductDAO {
	
	// 1차카테고리 출력
	public List<CategoryVO> mainCGList() throws Exception;
	
	// 2차카테고리츨력
	public List<CategoryVO> subCGList(String cate_code) throws Exception;
	
	// 해당하는 카테고리명
	public String getCGName(String cate_code) throws Exception;
	
	// 해당카테고리에 해당하는 상품리스트
	public List<ProductVO> productList(Map map) throws Exception;
	
	// 해당 카테고리의 상품개수
	public int productCount(String cate_code) throws Exception;
	
	// 상품상세정보 읽기
	public ProductVO readProduct(int gds_no) throws Exception;
}	