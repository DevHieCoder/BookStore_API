package com.java.bookStore_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Category;
import com.java.bookStore_api.repository.CategoryRepository;
import com.java.bookStore_api.serviceImp.CategoryServiceImp;

@Service
public class CategoryService implements CategoryServiceImp {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public boolean existsByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return categoryRepository.existsByCategoryName(categoryName);
	}

	@Override
	public Category findByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return categoryRepository.findByCategoryName(categoryName);
	}

}
