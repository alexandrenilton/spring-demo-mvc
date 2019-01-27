package com.abelem.course.boot.service;

import java.util.List;

import com.abelem.course.boot.domain.Role;

public interface RoleService {
	void save(Role role);
	
	void update(Role role);
	
	void delete(Long id);
	
	Role findById(Long id);
	
	List<Role> findAll();

	boolean roleHasEmployees(Long id);
}
