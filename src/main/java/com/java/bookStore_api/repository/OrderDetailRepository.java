package com.java.bookStore_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.OrderDetail;

@Repository
public interface OrderDetailRepository  extends JpaRepository<OrderDetail, Integer>{
	List<OrderDetail> findByOrders_id(int id);
}
