package com.abelem.course.boot.service;

import java.time.LocalDate;
import java.util.List;

import com.abelem.course.boot.domain.Employee;
import com.abelem.course.boot.domain.Role;

public interface EmployeeService {
	void save(Employee employee);
	
	void update(Employee employee);
	
	void delete(Long id);
	
	Employee findById(Long id);
	
	List<Employee> findAll();

	List<Employee> findByName(String name);

	List<Employee>  findByRole(Long roleId);

	List<Employee>  findByDates(LocalDate in, LocalDate out);
}
