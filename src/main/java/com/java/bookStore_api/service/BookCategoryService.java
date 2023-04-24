package com.java.bookStore_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.BookCategory;
import com.java.bookStore_api.repository.BookCategoryRepository;
import com.java.bookStore_api.serviceImp.BookCategoryServiceImp;

@Service
public class BookCategoryService implements BookCategoryServiceImp{
	
	@Autowired
	BookCategoryRepository bookCategoryRepository;

	@Override
	public List<BookCategory> findAllByBookId(int id) {
		// TODO Auto-generated method stub
		return bookCategoryRepository.findAllByBookId(id);
	}

	@Override
	public void deleteAll(BookCategory bookCategory) {
		// TODO Auto-generated method stub
		bookCategoryRepository.delete(bookCategory);
	}

	

}
