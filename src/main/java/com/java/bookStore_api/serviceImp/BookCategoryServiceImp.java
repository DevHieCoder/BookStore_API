package com.java.bookStore_api.serviceImp;

import java.util.List;

import com.java.bookStore_api.entity.BookCategory;

public interface BookCategoryServiceImp {
	public List<BookCategory> findAllByBookId(int id);
	public void deleteAll(BookCategory bookCategory);
}
