package com.java.bookStore_api.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	boolean existsByEmail(String email);
	User findByPassword(String password);
	boolean existsByUsername(String userName);
	User findByUsername(String userName);
	User findByEmail(String email);
	@Query(value = "call GetInfoUsers()", nativeQuery = true)
	List<Map<String, ?>> getInfoUsers();
}
