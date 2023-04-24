package com.java.bookStore_api.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query(value = "call GetInfoBooks()", nativeQuery = true)
	List<Map<String, ?>> getInfoBooks();
	boolean existsByName(String bookName);
	
}

