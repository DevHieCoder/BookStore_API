package com.java.bookStore_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.bookStore_api.entity.Role;
import com.java.bookStore_api.serviceImp.RoleServiceImp;

@RestController
@RequestMapping("/api/role")
public class RoleCotnroller {
	
	@Autowired
	RoleServiceImp roleServiceImp;
	
	@GetMapping("/allRole")
	public ResponseEntity<?> getAllRole() {
		List<Role> listRoles = roleServiceImp.getAllRole();
		
		return new ResponseEntity<List<Role>>(listRoles, HttpStatus.OK);
	}
	
}
