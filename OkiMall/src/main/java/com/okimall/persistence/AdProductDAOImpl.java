package com.okimall.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.okimall.domain.CategoryVO;
import com.okimall.domain.ProductVO;
import com.okimall.util.SearchCriteria;

@Repository
public class AdProductDAOImpl implements AdProductDAO {
	
	@Autowired
	SqlSession session;
	
	public final static String NS ="com.okimall.mappers.AdProductMapper";
	
	// 1차카테고리
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		
		return session.selectList(NS+".mainCGList");
	}
	
	// 1차 카테고리에 따른 2차카테고리
	@Override
	public List<CategoryVO> subCGList(String cate_code) throws Exception {
		
		return session.selectList(NS+".subCGList", cate_code);
		
	}
	
	// 상품등록
	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		
		session.insert(NS+".insertProduct", vo);
		
	}
	
	// 상품리스트
	@Override
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception {
		
		return session.selectList(NS+".searchListProduct", cri);
	}
	
	// 검색조건에 맞는 상품개수
	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
		
		return session.selectOne(NS+".searchListCount", cri);
	}
	
	// 상품정보
	@Override
	public ProductVO readProduct(int gds_no) throws Exception {
		
		return session.selectOne(NS+".readProduct", gds_no);
	
	}

	// 상품수정
	@Override
	public void editProduct(ProductVO vo) throws Exception {
		
		session.update(NS+".editProduct", vo);
		
	}
	
	// 상품삭제
	@Override
	public void deleteProduct(int gds_no) throws Exception {
		
		session.delete(NS +".deleteProduct", gds_no);
		
	}
	
	// 선택한 상품수정
	@Override
	public void editChecked(Map<String, Object> map) throws Exception {
		
		session.update(NS+".editChecked", map);
		
	}
	
	
	

}
