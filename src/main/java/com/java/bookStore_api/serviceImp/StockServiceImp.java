package com.java.bookStore_api.serviceImp;

import java.util.List;
import java.util.Optional;

import com.java.bookStore_api.entity.Stock;

public interface StockServiceImp {
	public List<Stock> getAllStock();
	public Stock createStock(Stock stock);
	public Stock findByName(String stockName);
	public Optional<Stock> findById(int id);
	public void deleteById(int id); 
	public boolean existsByName(String stockName);
}
