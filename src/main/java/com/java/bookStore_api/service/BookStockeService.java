package com.java.bookStore_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Book;
import com.java.bookStore_api.entity.BookStock;
import com.java.bookStore_api.repository.BookStockRepository;
import com.java.bookStore_api.serviceImp.BookStockServiceImp;

@Service
public class BookStockeService implements BookStockServiceImp{
	
	@Autowired
	BookStockRepository bookStockRepository;
	
	@Override
	public BookStock createBookStock(BookStock bookStock) {
		// TODO Auto-generated method stub
		return bookStockRepository.save(bookStock);
	}

	@Override
	public BookStock findByBook(Book book) {
		// TODO Auto-generated method stub
		return bookStockRepository.findByBook(book);
	}

	@Override
	public List<BookStock> findAllByBookId(int id) {
		// TODO Auto-generated method stub
		return bookStockRepository.findAllByBookId(id);
	}

	@Override
	public void deleteAll(BookStock bookStock) {
		// TODO Auto-generated method stub
		bookStockRepository.delete(bookStock);
	}

	@Override
	public List<BookStock> findAllByStockId(int id) {
		// TODO Auto-generated method stub
		return bookStockRepository.findAllByStockId(id);
	}

	@Override
	public List<Map<String, ?>> getBookQuantity() {
		// TODO Auto-generated method stub
		return bookStockRepository.getBookQuantity();
	}

}
