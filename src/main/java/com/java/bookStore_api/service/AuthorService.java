package com.java.bookStore_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Author;
import com.java.bookStore_api.repository.AuthorRepository;
import com.java.bookStore_api.serviceImp.AuthorServiceImp;

@Service
public class AuthorService implements AuthorServiceImp {

	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public Author addAuthor(Author author) {
		// TODO Auto-generated method stub
		return authorRepository.save(author);
	}

	@Override
	public boolean existsByAuthorName(String authorName) {
		// TODO Auto-generated method stub
		return authorRepository.existsByAuthorName(authorName);
	}

	@Override
	public Author findByAuthorName(String authorName) {
		// TODO Auto-generated method stub
		return authorRepository.findByAuthorName(authorName);
	}

}
