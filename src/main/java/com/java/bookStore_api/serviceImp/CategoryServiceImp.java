package com.java.bookStore_api.serviceImp;

import com.java.bookStore_api.entity.Category;

public interface CategoryServiceImp {
	public Category addCategory(Category category);
	public boolean existsByCategoryName(String categoryName);
	public Category findByCategoryName(String categoryName);
}
