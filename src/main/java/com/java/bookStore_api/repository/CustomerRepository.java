package com.java.bookStore_api.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByUserId(int id);
	@Query(value = "call GetAllCustomer()", nativeQuery = true)
	List<Map<String, ?>> getAllCustomer();
	void deleteByUser_Id(int userId);
}
