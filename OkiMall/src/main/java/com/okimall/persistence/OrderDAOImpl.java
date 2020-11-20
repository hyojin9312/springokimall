package com.okimall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.okimall.domain.OrderDetailVO;
import com.okimall.domain.OrderListVO;
import com.okimall.domain.OrderReadDetailVO;
import com.okimall.domain.OrderVO;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	@Autowired
	private SqlSession session;
	public final static String NS ="com.okimall.mappers.orderMapper";
	
	// 주문코드 가져오기
	@Override
	public int readOrderCode() throws Exception {
		
		return session.selectOne(NS+".readOrderCode");
	}
	
	// 주문내역 추가
	@Override
	public void addOrderDetail(OrderDetailVO vo) throws Exception {
		
		session.insert(NS+".addOrderDetail", vo);
		
	}
	
	//주문정보추가
	@Override
	public void addOrder(OrderVO vo) throws Exception {
		
		session.insert(NS+".addOrder", vo);
		
	}
	
	// 주문목록
	@Override
	public List<OrderListVO> orderList(String mb_id) throws Exception {
		
		return session.selectList(NS+".orderList", mb_id);
	}
	
	// 주문상세 정보
	@Override
	public List<OrderReadDetailVO> readOrder(int ord_no) throws Exception {
		
		return session.selectList(NS+".readOrder", ord_no);
		
	}
	
	// 주문자 정보
	@Override
	public OrderVO getOrder(int ord_no) throws Exception {
		
		return session.selectOne(NS+".getOrder", ord_no);
		
	}
	
	// 관리자 주문목록
	@Override
	public List<OrderListVO> adorderList(OrderListVO vo) throws Exception {
		
		return session.selectList(NS+".adorderList", vo);
		
	}
	

	
}
