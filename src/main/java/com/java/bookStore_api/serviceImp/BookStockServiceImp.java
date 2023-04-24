package com.java.bookStore_api.serviceImp;

import java.util.List;
import java.util.Map;

import com.java.bookStore_api.entity.Book;
import com.java.bookStore_api.entity.BookStock;

public interface BookStockServiceImp {
	public BookStock createBookStock(BookStock bookStock);
	public BookStock findByBook(Book book);
	public List<BookStock> findAllByBookId(int id);
	public List<BookStock> findAllByStockId(int id);
	public void deleteAll(BookStock bookStock);
	public List<Map<String, ?>> getBookQuantity();
}
