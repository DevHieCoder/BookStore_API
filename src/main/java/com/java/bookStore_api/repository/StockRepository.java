package com.java.bookStore_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>{
	Stock findByName(String stockName);
	boolean existsByName(String stockName);
}
