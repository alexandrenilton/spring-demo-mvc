package com.abelem.course.boot.dao;

import java.time.LocalDate;
import java.util.List;

import com.abelem.course.boot.domain.Employee;

public interface EmployeeDao {
	void save(Employee employee);
	
	void update(Employee employee);
	
	void delete(Long id);
	
	Employee findById(Long id);
	
	List<Employee> findAll();

	List<Employee> findByName(String name);

	List<Employee> findByRole(Long roleId);

	List<Employee> findByDateInDateOut(LocalDate in, LocalDate out);

	List<Employee> findByDataIn(LocalDate in);

	List<Employee> findByDataOut(LocalDate out);
}
