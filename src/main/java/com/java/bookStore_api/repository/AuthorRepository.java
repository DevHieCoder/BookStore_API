package com.java.bookStore_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
 	boolean existsByAuthorName(String authorName);
	Author findByAuthorName(String authorName);
}
