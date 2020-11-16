package com.okimall.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.okimall.domain.CategoryVO;
import com.okimall.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Inject
	private SqlSession session;
	public final static String NS = "com.okimall.mappers.productMapper";
	
	// 1차카테고리 출력
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		
		return session.selectList(NS+".mainCGList");
	
	}
	
	// 2차 카테고리 출력
	@Override
	public List<CategoryVO> subCGList(String cate_code) throws Exception {
		
		return session.selectList(NS +".subCGList", cate_code);
	
	}
	
	// 해당카테고리명
	@Override
	public String getCGName(String cate_code) throws Exception {
		
		return  session.selectOne(NS+".getCGName", cate_code);
	
	}
	
	// 해당카테고리에 해당하는 상품리스트
	@Override
	public List<ProductVO> productList(Map map) throws Exception {
		
		return session.selectList(NS+".productList", map);
	
	}
	
	// 해당 카테고리의 상품개수
	@Override
	public int productCount(String cate_code) throws Exception {
		
		return session.selectOne(NS+".productCount", cate_code);
	
	}
	
	// 상품상세정보 읽기
	@Override
	public ProductVO readProduct(int gds_no) throws Exception {
		
		return session.selectOne(NS + ".readProduct", gds_no);
	}

}
