package com.java.bookStore_api.serviceImp;

import java.util.List;

import com.java.bookStore_api.entity.OrderDetail;

public interface OrderDetailServiceImp {
	public OrderDetail createOrderDetail(OrderDetail orderDetail);
	List<OrderDetail> findByOrders_id(int id);
	void deleteOrderDetail(OrderDetail orderDetail);
}
