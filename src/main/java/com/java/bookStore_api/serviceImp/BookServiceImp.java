package com.java.bookStore_api.serviceImp;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.java.bookStore_api.entity.Book;

public interface BookServiceImp {
	public Book addBook(Book book);
	public List<Map<String, ?>> getInfoBooks();
	public List<Book> getAllBook();
	public Optional<Book> findById(int id);
	public void deleteById(int id);
	public boolean existsByName(String bookName);
}
