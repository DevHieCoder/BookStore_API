package com.java.bookStore_api.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Book;
import com.java.bookStore_api.entity.BookStock;

@Repository
public interface BookStockRepository extends JpaRepository<BookStock, Integer>{
	BookStock findByBook(Book book);
	List<BookStock> findAllByBookId(int id);
	List<BookStock> findAllByStockId(int id);
	@Query(value = "call GetBookQuantity()", nativeQuery = true)
	List<Map<String, ?>> getBookQuantity();
}
