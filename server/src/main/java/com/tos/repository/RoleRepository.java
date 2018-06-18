package com.tos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tos.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByName(String name);

}
