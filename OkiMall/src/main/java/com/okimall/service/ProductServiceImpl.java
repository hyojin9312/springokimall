package com.okimall.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.okimall.domain.CategoryVO;
import com.okimall.domain.ProductVO;
import com.okimall.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Inject
	private ProductDAO dao;
	
	// 1차 카테고리 출력
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		
		return dao.mainCGList();
		
	}

	@Override
	public List<CategoryVO> subCGList(String cate_code) throws Exception {
		
		return dao.subCGList(cate_code);
	
	}
	
	// 해당하는 카테고리명
	@Override
	public String getCGName(String cate_code) throws Exception {
		
		return dao.getCGName(cate_code);
	
	}
	
	// 해당카테고리에 해당하는 상품리스트
	@Override
	public List<ProductVO> productList(Map map) throws Exception {
		
		return dao.productList(map);
	
	}
	
	// 카테고리에 해당하는 상품개수
	@Override
	public int productCount(String cate_code) throws Exception {
		
		return dao.productCount(cate_code);
	
	}
	
	// 상품상세정보 읽기
	@Override
	public ProductVO readProduct(int gds_no) throws Exception {
		
		return dao.readProduct(gds_no);
	
	}

}
