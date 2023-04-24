package com.java.bookStore_api.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
	@Query(value = "call GetOrders()", nativeQuery = true)
	List<Map<String, ?>> getOrdes();

}
