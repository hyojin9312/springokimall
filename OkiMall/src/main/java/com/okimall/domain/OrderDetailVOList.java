package com.okimall.domain;

import java.util.List;

public class OrderDetailVOList {
	
	private List<OrderDetailVO> orderDetailList;
	
	// getter, setter
	public List<OrderDetailVO> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetailVO> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	
	// toString
	@Override
	public String toString() {
		return "OrderDetailVOList [orderDetailList=" + orderDetailList + "]";
	}
	
	
	
}
