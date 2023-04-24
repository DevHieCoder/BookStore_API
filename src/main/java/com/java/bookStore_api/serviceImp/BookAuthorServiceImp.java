package com.java.bookStore_api.serviceImp;

import java.util.List;

import com.java.bookStore_api.entity.BookAuthor;

public interface BookAuthorServiceImp {
	public List<BookAuthor> findAllByBookId(int id);
	public void deleteAll(BookAuthor bookAuthor);
}
