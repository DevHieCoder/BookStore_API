package com.java.bookStore_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.java.bookStore_api.entity.Customer;
import com.java.bookStore_api.entity.User;
import com.java.bookStore_api.serviceImp.CustomerServiceImp;
import com.java.bookStore_api.serviceImp.UserServiceImp;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	CustomerServiceImp customerServiceImp;
	
	@Autowired
	UserServiceImp userServiceImp;
	
	@GetMapping("/allCustomer")
	public ResponseEntity<?> allCustomer() {
		List<Map<String, ?>> customers = customerServiceImp.getAllCustomer();
		
		return new ResponseEntity<List<Map<String, ?>>>(customers, HttpStatus.OK);
	}
	
	@PostMapping("/editCustomer")
	public RedirectView editCustomer(@RequestParam("customerName") String customerName, @RequestParam("phoneCustomer") Integer phoneCustomer,
	        @RequestParam("address") String address, @RequestParam("email") String email, @RequestParam("userName") String userName) {
		
		User user = userServiceImp.findByEmail(email);
		if (user != null) {
//	    	Lưu thông tin Customer
	        Customer customer = customerServiceImp.findByUserId(user.getId());
	        if (customer == null) {
	            customer = new Customer();
	            customer.setUser(user);
	        }
	        customer.setFullName(customerName);
	        customer.setPhoneNumber(phoneCustomer);
	        customer.setAddress(address);
	        customerServiceImp.createCustomer(customer);
		}
		return new RedirectView("http://localhost:8081/home/editProfile?successMessage=Edit profile successfully!&userName=" + userName);
	}
	
}
