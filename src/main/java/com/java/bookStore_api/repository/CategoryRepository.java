package com.java.bookStore_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	boolean existsByCategoryName(String categoryName);
	Category findByCategoryName(String categoryName);
}
