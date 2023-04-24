package com.java.bookStore_api.serviceImp;

import java.util.List;
import java.util.Optional;

import com.java.bookStore_api.entity.Role;

public interface RoleServiceImp {
	public List<Role> getAllRole();
	public Optional<Role> findByRoleName(String roleName);
}
