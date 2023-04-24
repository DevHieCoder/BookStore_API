package com.java.bookStore_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.BookAuthor;

@Repository
public interface BookAuhorRepository extends JpaRepository<BookAuthor, Integer>{
	List<BookAuthor> findAllByBookId(int id);
}
