package com.java.bookStore_api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.bookStore_api.entity.Book;
import com.java.bookStore_api.entity.BookStock;
import com.java.bookStore_api.entity.Customer;
import com.java.bookStore_api.entity.OrderDetail;
import com.java.bookStore_api.entity.Orders;
import com.java.bookStore_api.entity.Payment;
import com.java.bookStore_api.entity.User;
import com.java.bookStore_api.serviceImp.BookServiceImp;
import com.java.bookStore_api.serviceImp.BookStockServiceImp;
import com.java.bookStore_api.serviceImp.CustomerServiceImp;
import com.java.bookStore_api.serviceImp.OrderDetailServiceImp;
import com.java.bookStore_api.serviceImp.OrdersServiceImp;
import com.java.bookStore_api.serviceImp.PayPalServiceImp;
import com.java.bookStore_api.serviceImp.PaymentServiceImp;
import com.java.bookStore_api.serviceImp.UserServiceImp;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	UserServiceImp userServiceImp;
	
	@Autowired
	CustomerServiceImp customerServiceImp;
	
	@Autowired
	OrdersServiceImp ordersServiceImp;
	
	@Autowired
	PaymentServiceImp paymentServiceImp;
	
	@Autowired
	BookServiceImp bookServiceImp;
	
	@Autowired
	OrderDetailServiceImp orderDetailServiceImp;
	
	@Autowired
	BookStockServiceImp bookStockServiceImp;
	
	@Autowired
	private PayPalServiceImp payPalServiceImp;

	
	@PostMapping("")
	public RedirectView orders(@RequestParam("customerName") String customerName, @RequestParam("phoneCustomer") Integer phoneCustomer,
	        @RequestParam("address") String address, @RequestParam("optionPayment") String optionPayment,
	        @RequestParam(name = "creditCardNumber", required = false) String creditCardNumber, 
	        @RequestParam("booksInCartJson") String bookNamesJson, @RequestParam("email") String email,
	        @RequestParam("totalPrice") Float totalPrice, @RequestParam("bookQuantitiesJson") String bookQuantitiesJson) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    List<String> bookNames = new ArrayList<>();
		/* String bookNamesString = null; */

	    try {
	        bookNames = objectMapper.readValue(bookNamesJson, new TypeReference<List<String>>() {});
			/* bookNamesString = String.join(", ", bookNames); */
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

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
	        customer.setCardNumber(creditCardNumber);

	        String existingPurchaseHistory = customer.getPurchaseHistory();
            List<String> existingBooks;

            // Kiểm tra nếu existingPurchaseHistory không phải null, thì tách chuỗi, ngược lại khởi tạo danh sách rỗng
            if (existingPurchaseHistory != null) {
                existingBooks = Arrays.asList(existingPurchaseHistory.split(", "));
            } else {
                existingBooks = new ArrayList<>();
            }

            // Tạo một danh sách mới để lưu trữ sách không trùng lặp
            List<String> nonDuplicateBooks = new ArrayList<>();

            // Kiểm tra từng sách trong bookNames, nếu không trùng lặp thì thêm vào danh sách nonDuplicateBooks
            for (String book : bookNames) {
                if (!existingBooks.contains(book)) {
                    nonDuplicateBooks.add(book);
                }
            }

            // Nếu nonDuplicateBooks không rỗng, nối chuỗi vào existingPurchaseHistory hoặc lưu trữ nonDuplicateBooksString nếu existingBooks rỗng
            if (!nonDuplicateBooks.isEmpty()) {
                String nonDuplicateBooksString = String.join(", ", nonDuplicateBooks);
                if (existingBooks.isEmpty()) {
                    customer.setPurchaseHistory(nonDuplicateBooksString);
                } else {
                    customer.setPurchaseHistory(existingPurchaseHistory + ", " + nonDuplicateBooksString);
                }
            } else {
                customer.setPurchaseHistory(existingPurchaseHistory != null ? existingPurchaseHistory : "");
            }

            customerServiceImp.createCustomer(customer);

//	        Lưu thông tin order
	        Orders order = new Orders();
	        order.setTotalAmount(totalPrice);
	        order.setShippingAddres(address);
	        order.setCustomer(customer);
	        Date currentDate = new Date();
	        order.setOrderDate(currentDate);
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(currentDate);
	        calendar.add(Calendar.DATE, 5);
	        Date deliveryDate = calendar.getTime();
	        order.setDeleveryDate(deliveryDate);
	        ordersServiceImp.createOrder(order);
	        
//	        Lưu thông tin payment
	        Payment payment = new Payment();
	        payment.setPaymentMethod(optionPayment);
	        payment.setPaymentDate(currentDate);
	        payment.setOrders(order);
	        paymentServiceImp.createPayment(payment);
	        
	        
//	        Lưu thông tin orderDetail
	        List<Integer> bookQuantities = new ArrayList<>();

	        try {
	            bookQuantities = objectMapper.readValue(bookQuantitiesJson, new TypeReference<List<Integer>>() {});
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        List<Book> books = bookServiceImp.getAllBook();
	        for (int i = 0; i < bookNames.size(); i++) {
	            String bookName = bookNames.get(i);
	            int bookQuantity = bookQuantities.get(i);

	            Book matchedBook = books.stream()
	                    .filter(book -> book.getName().equals(bookName))
	                    .findFirst()
	                    .orElse(null);

	            if (matchedBook != null) {
	                OrderDetail orderDetail = new OrderDetail();
	                orderDetail.setBook(matchedBook);
	                orderDetail.setOrders(order);
	                orderDetail.setPrice(matchedBook.getPrice());
	                orderDetail.setQuantity(bookQuantity);
	                orderDetailServiceImp.createOrderDetail(orderDetail);
	                
	                BookStock bookStock = bookStockServiceImp.findByBook(matchedBook);
	                if (bookStock != null) {
	                    int currentStock = bookStock.getQuantity();
	                    int updatedStock = currentStock - bookQuantity;
	                    bookStock.setQuantity(updatedStock);
	                    bookStockServiceImp.createBookStock(bookStock);
	                }
	            }
	        }
	        if (optionPayment.equalsIgnoreCase("paypal")) {
	            String cancelUrl = "http://localhost:8080/api/order/cancel?orderId=" + order.getId() + "&userName=" + user.getUsername();
	            String successUrl = "http://localhost:8080/api/order/success?orderId=" + order.getId() + "&userName=" + user.getUsername();
	            try {
	                com.paypal.api.payments.Payment paypalPayment = payPalServiceImp.createPayment(
	                        Double.valueOf(totalPrice),
	                        "USD",
	                        "paypal",
	                        "sale",
	                        "BookStore Order",
	                        cancelUrl,
	                        successUrl
	                );
	                List<com.paypal.api.payments.Links> links = paypalPayment.getLinks();
	                for (com.paypal.api.payments.Links link : links) {
	                    if (link.getRel().equalsIgnoreCase("approval_url")) {
	                        return new RedirectView(link.getHref());
	                    }
	                }
	            } catch (PayPalRESTException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    
	    String redirectUrl = "http://localhost:8081/home/orderResult?userName=" + user.getUsername();
	    return new RedirectView(redirectUrl);
	}
	
	@GetMapping("/success")
	public RedirectView successPayment(@RequestParam("orderId") Integer orderId, @RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			@RequestParam("userName") String userName) {
	    try {
	        com.paypal.api.payments.Payment payment = payPalServiceImp.executePayment(paymentId, payerId);
			/*
			 * if (payment.getState().equals("approved")) { Payment existsPayment =
			 * paymentServiceImp.findByOrderId(orderId); }
			 */
	        
	    } catch (PayPalRESTException e) {
	        e.printStackTrace();
	    }
	    String redirectUrl = "http://localhost:8081/home/orderResult?userName=" + userName;
	    return new RedirectView(redirectUrl);
	}
	
	@GetMapping("/cancel")
	public RedirectView cancelPayment(@RequestParam("orderId") Integer orderId, @RequestParam("userName") String userName) {
		Optional<Orders> orderOptional = ordersServiceImp.findById(orderId);
		if (orderOptional.isPresent()) {
			
			List<OrderDetail> orderDetails = orderDetailServiceImp.findByOrders_id(orderId);
			for (OrderDetail orderDetail : orderDetails) {
				BookStock bookStock = bookStockServiceImp.findByBook(orderDetail.getBook());
				if (bookStock != null) {
					int currentStock = bookStock.getQuantity();
					int updatedStock = currentStock + orderDetail.getQuantity();
					bookStock.setQuantity(updatedStock);
					bookStockServiceImp.createBookStock(bookStock);
				}
				orderDetailServiceImp.deleteOrderDetail(orderDetail);
			}
			Payment payment = paymentServiceImp.findByOrderId(orderId);
			paymentServiceImp.deletePayment(payment);
			
			ordersServiceImp.deleteById(orderId);
		}
		
	    String redirectUrl = "http://localhost:8081/home/cancelPayment?userName=" + userName;
	    return new RedirectView(redirectUrl);
	}
	
	@GetMapping("/getOrders")
	public ResponseEntity<?> getOrders() {
		List<Map<String, ?>> listOrder = ordersServiceImp.getOrders();
		
		return new ResponseEntity<List<Map<String, ?>>>(listOrder, HttpStatus.OK);
	}

}

