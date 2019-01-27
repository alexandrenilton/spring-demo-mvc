package com.abelem.course.boot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abelem.course.boot.dao.EmployeeDao;
import com.abelem.course.boot.domain.Employee;

@Service
@Transactional (readOnly = false) /* false é o valor padrão*/
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
		
	@Transactional(readOnly = false)
	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(Long id) {
		employeeDao.delete(id);
	}

	@Override
	public Employee findById(Long id) {
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

	@Override
	public List<Employee> findByName(String name) {
		return employeeDao.findByName(name);
	}

	@Override
	public List<Employee> findByRole(Long roleId) {
		return employeeDao.findByRole(roleId);
	}

	@Override
	public List<Employee> findByDates(LocalDate in, LocalDate out) {
		if (in != null && out != null) {
			return employeeDao.findByDateInDateOut(in, out);
		} else if (in != null) {
			return employeeDao.findByDataIn(in);
		} else if (out != null) {
			return employeeDao.findByDataOut(out);
		} else {
			return new ArrayList<>();
		}
	}
}
