package com.java.bookStore_api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.java.bookStore_api.entity.BookStock;
import com.java.bookStore_api.entity.Stock;
import com.java.bookStore_api.serviceImp.BookStockServiceImp;
import com.java.bookStore_api.serviceImp.StockServiceImp;

@RestController
@RequestMapping("/api/stock")
public class StockController {
	
	@Autowired
	StockServiceImp stockServiceImp;
	
	@Autowired
	BookStockServiceImp bookStockServiceImp;
	
	@GetMapping("/allStock")
	public ResponseEntity<?> getAllStock() {
		List<Stock> listStock = stockServiceImp.getAllStock();
		
		return new ResponseEntity<List<Stock>>(listStock, HttpStatus.OK);
	}
	
	@GetMapping("/getStockById")
	public ResponseEntity<?> getStockById(@RequestParam("id") Integer id) {
		List<Stock> listStock = stockServiceImp.getAllStock();
		Optional<Stock> newStock = java.util.Optional.empty();
		for (Stock stock : listStock) {
			if (stock.getId() == id) {
				newStock = stockServiceImp.findById(id);
			}
		}
		return new ResponseEntity<Optional<Stock>>(newStock, HttpStatus.OK);
	}
	
	@PostMapping("/addStock")
	public RedirectView addStock(@RequestParam("stockName") String stockName,
			@RequestParam("stockPhone") Integer stockPhone, @RequestParam("stockAddress") String stockAddress) {
		boolean isSuccess = true;
		
		List<Stock> listStocks = stockServiceImp.getAllStock();
		
		for (Stock stock : listStocks) {
			if (stock.getName().equalsIgnoreCase(stockName)) {
				isSuccess = false;
				break;
			}
		}
		
		if (isSuccess) {
			Stock newStock = new Stock();
			newStock.setName(stockName);
			newStock.setPhone(stockPhone);
			newStock.setAddress(stockAddress);
			stockServiceImp.createStock(newStock);
		}
		
		String redirectUrl = "http://localhost:8081/adminHome/addStock";
		if (!isSuccess) {
			redirectUrl += "?fail=true";
		} else {
			redirectUrl += "?success=true";
		}
		
		return new RedirectView(redirectUrl);
	}
	
	@PostMapping("/updateStock/{id}")
	public RedirectView updateStock(@PathVariable Integer id, @RequestParam("stockName") String stockName,
			@RequestParam("stockPhone") Integer stockPhone, @RequestParam("stockAddress") String stockAddress) {

		Optional<Stock> stockOptional = stockServiceImp.findById(id);
		Stock stock = stockOptional.get();

//		Kiểm tra tên stock trùng
		if (!stock.getName().equalsIgnoreCase(stockName) && stockServiceImp.existsByName(stockName)) {
			String redirectUrl = "http://localhost:8081/adminHome/updateStock?id=" + id + "&fail=true";
			return new RedirectView(redirectUrl);
		}
		stock.setName(stockName);
		stock.setPhone(stockPhone);
		stock.setAddress(stockAddress);
		stockServiceImp.createStock(stock);
		String redirectUrl = "http://localhost:8081/adminHome/stock?updateSuccess=true";

		return new RedirectView(redirectUrl);
	}
	
	@GetMapping("/delStock/{id}")
	public RedirectView delStock(@PathVariable Integer id) {
		List<BookStock> listBookStocks = bookStockServiceImp.findAllByStockId(id);
		for (BookStock bookStock : listBookStocks) {
			bookStockServiceImp.deleteAll(bookStock);
		}
		stockServiceImp.deleteById(id);
		
		String redirectUrl = "http://localhost:8081/adminHome/stock?delSuccess=true";
		return new RedirectView(redirectUrl);
	}
	
	@GetMapping("/getQuantity")
	public ResponseEntity<?> getQuantity() {
		List<Map<String, ?>> booksQuantity = bookStockServiceImp.getBookQuantity();
		
		return new ResponseEntity<List<Map<String, ?>>>(booksQuantity, HttpStatus.OK);
	}
	
}
