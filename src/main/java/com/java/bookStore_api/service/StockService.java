package com.java.bookStore_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Stock;
import com.java.bookStore_api.repository.StockRepository;
import com.java.bookStore_api.serviceImp.StockServiceImp;

@Service
public class StockService implements StockServiceImp {

	@Autowired
	StockRepository stockRepository;
	
	@Override
	public List<Stock> getAllStock() {
		// TODO Auto-generated method stub
		return stockRepository.findAll();
	}

	@Override
	public Stock createStock(Stock stock) {
		// TODO Auto-generated method stub
		return stockRepository.save(stock);
	}

	@Override
	public Stock findByName(String stockName) {
		// TODO Auto-generated method stub
		return stockRepository.findByName(stockName);
	}

	@Override
	public Optional<Stock> findById(int id) {
		// TODO Auto-generated method stub
		return stockRepository.findById(id);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		stockRepository.deleteById(id);
	}

	@Override
	public boolean existsByName(String stockName) {
		// TODO Auto-generated method stub
		return stockRepository.existsByName(stockName);
	}

}
