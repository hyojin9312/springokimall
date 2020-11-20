package com.okimall.persistence;

import java.util.List;

import com.okimall.domain.OrderDetailVO;
import com.okimall.domain.OrderListVO;
import com.okimall.domain.OrderReadDetailVO;
import com.okimall.domain.OrderVO;

public interface OrderDAO {
	
	// 주문코드 가져오기
	public int readOrderCode() throws Exception;
	
	// 주문내역 추가
	public void addOrderDetail(OrderDetailVO vo)throws Exception;
	
	// 주문정보 추가
	public void addOrder(OrderVO vo) throws Exception;
	
	// 주문목록
	public List<OrderListVO> orderList(String mb_id) throws Exception;
	
	// 주문 상세 정보
	public List<OrderReadDetailVO> readOrder(int ord_no) throws Exception;
	
	// 주문자 정보
	public OrderVO getOrder(int ord_no) throws Exception;
	
	// 관리자 주문목록
	public List<OrderListVO> adorderList(OrderListVO vo) throws Exception;
}
