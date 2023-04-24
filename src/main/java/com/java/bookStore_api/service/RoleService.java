package com.java.bookStore_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bookStore_api.entity.Role;
import com.java.bookStore_api.repository.RoleRepository;
import com.java.bookStore_api.serviceImp.RoleServiceImp;

@Service
public class RoleService implements RoleServiceImp {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return roleRepository.findByRoleName(roleName);
	}

}
