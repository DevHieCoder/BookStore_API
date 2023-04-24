package com.java.bookStore_api.serviceImp;

import com.java.bookStore_api.entity.Payment;

public interface PaymentServiceImp {
	public Payment createPayment(Payment payment);
	public Payment findByOrderId(int id);
	void deletePayment(Payment payment);
}
