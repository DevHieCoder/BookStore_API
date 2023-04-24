package com.java.bookStore_api.serviceImp;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.java.bookStore_api.entity.User;

public interface UserServiceImp {
	public User createUser(User user);
	public boolean existsByEmail(String email);
	public User findByPassword(String password);
	public List<Map<String, ?>> getInfoUsers();
	public Optional<User> getUserById(int id);
	public boolean existsByUsername(String userName);
	public User findByUsername(String userName);
	public void deleteById(int id);
	public User findByEmail(String email);
	public List<User> findAll();
}
