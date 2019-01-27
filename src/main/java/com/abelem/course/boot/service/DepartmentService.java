package com.abelem.course.boot.service;

import java.util.List;

import com.abelem.course.boot.domain.Department;
import com.abelem.course.boot.domain.Role;

public interface DepartmentService {
	void save(Department department);
	
	void update(Department department);
	
	void delete(Long id);
	
	Department findById(Long id);
	
	List<Department> findAll();

	boolean departmentHasRole(Long id);
}
