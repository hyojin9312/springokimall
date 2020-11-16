package com.okimall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okimall.domain.OrderDetailVO;
import com.okimall.domain.OrderDetailVOList;
import com.okimall.domain.OrderListVO;
import com.okimall.domain.OrderReadDetailVO;
import com.okimall.domain.OrderVO;
import com.okimall.persistence.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO dao;
	
	@Autowired
	private CartService cartservice;
	
	// 상품구매 - 장바구니 거치지않고 바로 진행했을경우
	@Transactional
	@Override
	public void addOrder(OrderVO order, OrderDetailVOList orderDetailList) throws Exception {
		//주문코드 가져오기
		int ord_no = dao.readOrderCode();
		
		//주문정보 추가
		order.setOrd_no(ord_no);
		dao.addOrder(order);
		
		//주문내역추가
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		for(int i=0; i<list.size(); i++) {
			
			OrderDetailVO orderDetail = list.get(i);
			orderDetail.setOrd_no(ord_no);
			dao.addOrderDetail(orderDetail);
			
		}
		
	}
	
	// 주문추가 장바구니
	@Transactional
	@Override
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mb_id) throws Exception {
		
		int ord_no = dao.readOrderCode();
		
		// 주문정보추가/ 저장
		order.setOrd_no(ord_no);
		dao.addOrder(order);
		
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		for(int i=0; i<list.size(); i++) {
			
			OrderDetailVO orderDetail = list.get(i);
			orderDetail.setOrd_no(ord_no);
			// 주문상세정보 저장
			dao.addOrderDetail(orderDetail);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mb_id", mb_id);
			map.put("gds_no", orderDetail.getGds_no());
			
			cartservice.deleteCartOrder(map);
		}
		
		
	}
	
	// 주문목록
	@Override
	public List<OrderListVO> orderList(String mb_id) throws Exception {
		
		return dao.orderList(mb_id);
	}

	// 주문상세 정보
	@Override
	public List<OrderReadDetailVO> readOrder(int ord_no) throws Exception {
		
		return dao.readOrder(ord_no);
	
	}

	// 주문자 정보
	@Override
	public OrderVO getOrder(int ord_no) throws Exception {
		
		return dao.getOrder(ord_no);
		
	}
	
	// 관리자주문목록
	@Override
	public List<OrderListVO> adorderlist(OrderListVO vo) throws Exception {
		
		return dao.adorderlist(vo);
	
	}
	
	
	
}
