package com.java.bookStore_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.OrderDetail;
import com.java.bookStore_api.repository.OrderDetailRepository;
import com.java.bookStore_api.serviceImp.OrderDetailServiceImp;

@Service
public class OrderDetailService implements OrderDetailServiceImp {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public OrderDetail createOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return orderDetailRepository.save(orderDetail);
	}

	@Override
	public List<OrderDetail> findByOrders_id(int id) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findByOrders_id(id);
	}

	@Override
	public void deleteOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		orderDetailRepository.delete(orderDetail);
	}

}
