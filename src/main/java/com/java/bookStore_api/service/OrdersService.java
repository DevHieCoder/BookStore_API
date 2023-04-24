package com.java.bookStore_api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Orders;
import com.java.bookStore_api.repository.OrdersRepository;
import com.java.bookStore_api.serviceImp.OrdersServiceImp;

@Service
public class OrdersService implements OrdersServiceImp {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Override
	public Orders createOrder(Orders order) {
		// TODO Auto-generated method stub
		return ordersRepository.save(order);
	}

	@Override
	public List<Map<String, ?>> getOrders() {
		// TODO Auto-generated method stub
		return ordersRepository.getOrdes();
	}

	@Override
	public Optional<Orders> findById(int id) {
		// TODO Auto-generated method stub
		return ordersRepository.findById(id);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		ordersRepository.deleteById(id);
	}

}
