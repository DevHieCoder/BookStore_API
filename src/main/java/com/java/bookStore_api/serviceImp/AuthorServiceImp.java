package com.java.bookStore_api.serviceImp;

import com.java.bookStore_api.entity.Author;

public interface AuthorServiceImp {
	public Author addAuthor(Author author);
	public boolean existsByAuthorName(String authorName);
	public Author findByAuthorName(String authorName);
}
