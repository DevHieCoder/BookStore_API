package com.java.bookStore_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	Payment findByOrders_Id(Integer orderId);
}
