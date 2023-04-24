package com.java.bookStore_api.serviceImp;

import java.util.List;
import java.util.Map;

import com.java.bookStore_api.entity.Customer;

public interface CustomerServiceImp {
	public Customer createCustomer(Customer customer);
	public List<Customer> getAllCustomers();
	public Customer findByUserId(int id);
	public List<Map<String, ?>> getAllCustomer();
	public void deleteByUser_Id(int userId);
}
