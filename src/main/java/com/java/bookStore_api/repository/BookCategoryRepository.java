package com.java.bookStore_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer>{
	List<BookCategory> findAllByBookId(int id);
}
