package com.abelem.course.boot.dao;

import java.util.List;

import com.abelem.course.boot.domain.Department;

public interface DepartmentDao {
	void save(Department department);
	void update(Department department);
	void delete(Long id);
	Department findById(Long id);
	List<Department> findAll();
}
