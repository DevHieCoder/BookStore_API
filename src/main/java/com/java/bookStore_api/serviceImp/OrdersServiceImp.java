package com.java.bookStore_api.serviceImp;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.java.bookStore_api.entity.Orders;

public interface OrdersServiceImp {
	public Orders createOrder(Orders order);
	public List<Map<String, ?>> getOrders();
	public Optional<Orders> findById(int id);
	void deleteById(int id);
}
