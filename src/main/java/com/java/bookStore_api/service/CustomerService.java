package com.java.bookStore_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Customer;
import com.java.bookStore_api.repository.CustomerRepository;
import com.java.bookStore_api.serviceImp.CustomerServiceImp;

@Service
public class CustomerService implements CustomerServiceImp {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer findByUserId(int id) {
		// TODO Auto-generated method stub
		return customerRepository.findByUserId(id);
	}

	@Override
	public List<Map<String, ?>> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.getAllCustomer();
	}

	@Override
	public void deleteByUser_Id(int userId) {
		// TODO Auto-generated method stub
		customerRepository.deleteByUser_Id(userId);
	}

}
