package com.java.bookStore_api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.User;
import com.java.bookStore_api.repository.UserRepository;
import com.java.bookStore_api.serviceImp.UserServiceImp;

@Service
public class UserService implements UserServiceImp {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		
		return userRepository.save(user);
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}

	@Override
	public User findByPassword(String password) {
		// TODO Auto-generated method stub
		return userRepository.findByPassword(password);
	}

	@Override
	public List<Map<String, ?>> getInfoUsers() {
		// TODO Auto-generated method stub
		return userRepository.getInfoUsers();
	}

	@Override
	public Optional<User> getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public boolean existsByUsername(String userName) {
		// TODO Auto-generated method stub
		return userRepository.existsByUsername(userName);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(userName);
	}

}
