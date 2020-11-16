package com.okimall.service;

import java.util.List;

import com.okimall.domain.OrderDetailVOList;
import com.okimall.domain.OrderListVO;
import com.okimall.domain.OrderReadDetailVO;
import com.okimall.domain.OrderVO;

public interface OrderService {
	
	// 주문정보 추가(바로구매)
	public void addOrder(OrderVO order, OrderDetailVOList orderDetailList) throws Exception;
	
	// 주문정보추가(장바구니)
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mb_id) throws Exception;
	
	// 주문목록
	public List<OrderListVO> orderList(String mb_id) throws Exception;
	
	// 주문 상세 정보
	public List<OrderReadDetailVO> readOrder(int ord_no) throws Exception;
	
	// 주문자 정보
	public OrderVO getOrder(int ord_no) throws Exception;
	
	// 관리자 주문목록
	public List<OrderListVO> adorderlist(OrderListVO vo) throws Exception;
}
