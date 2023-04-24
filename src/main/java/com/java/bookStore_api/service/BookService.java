package com.java.bookStore_api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Book;
import com.java.bookStore_api.repository.BookRepository;
import com.java.bookStore_api.serviceImp.BookServiceImp;

@Service
public class BookService implements BookServiceImp {

	@Autowired
	BookRepository bookRepository;
	
	@Override
	public Book addBook(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public List<Map<String, ?>> getInfoBooks() {
		// TODO Auto-generated method stub
		return bookRepository.getInfoBooks();
	}

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> findById(int id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(id);
	}

	@Override
	public boolean existsByName(String bookName) {
		// TODO Auto-generated method stub
		return bookRepository.existsByName(bookName);
	}

}
