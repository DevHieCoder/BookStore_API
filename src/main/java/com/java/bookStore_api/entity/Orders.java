package com.java.bookStore_api.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "total_amount")
	private float totalAmount;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "expected_delivery_date")
	private Date deleveryDate;
	
	@Column(name = "shipping_address")
	private String shippingAddres;
	
	@ManyToOne
	@JoinColumn(name = "id_customer")
	private Customer customer;
	
	@OneToOne(mappedBy = "orders", cascade = CascadeType.REMOVE)
	private Payment payment;
	
	@OneToMany(mappedBy = "orders", cascade = CascadeType.REMOVE)
	private Set<OrderDetail> orderDetails;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeleveryDate() {
		return deleveryDate;
	}

	public void setDeleveryDate(Date deleveryDate) {
		this.deleveryDate = deleveryDate;
	}

	public String getShippingAddres() {
		return shippingAddres;
	}

	public void setShippingAddres(String shippingAddres) {
		this.shippingAddres = shippingAddres;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
