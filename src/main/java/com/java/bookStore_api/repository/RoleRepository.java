package com.java.bookStore_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByRoleName(String roleName);
}
