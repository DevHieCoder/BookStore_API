package com.java.bookStore_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Payment;
import com.java.bookStore_api.repository.PaymentRepository;
import com.java.bookStore_api.serviceImp.PaymentServiceImp;

@Service
public class PaymentService implements PaymentServiceImp {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public Payment createPayment(Payment payment) {
		// TODO Auto-generated method stub
		return paymentRepository.save(payment);
	}

	@Override
	public Payment findByOrderId(int id) {
		// TODO Auto-generated method stub
		return paymentRepository.findByOrders_Id(id);
	}

	@Override
	public void deletePayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepository.delete(payment);
	}

}
