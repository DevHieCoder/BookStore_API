package com.java.bookStore_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.BookAuthor;
import com.java.bookStore_api.repository.BookAuhorRepository;
import com.java.bookStore_api.serviceImp.BookAuthorServiceImp;

@Service
public class BookAuthorService implements BookAuthorServiceImp{
	
	@Autowired
	BookAuhorRepository bookAuhorRepository;

	@Override
	public List<BookAuthor> findAllByBookId(int id) {
		// TODO Auto-generated method stub
		return bookAuhorRepository.findAllByBookId(id);
	}

	@Override
	public void deleteAll(BookAuthor bookAuthor) {
		// TODO Auto-generated method stub
		bookAuhorRepository.delete(bookAuthor);
	}

}
