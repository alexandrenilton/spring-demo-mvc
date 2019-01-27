package com.abelem.course.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abelem.course.boot.dao.DepartmentDao;
import com.abelem.course.boot.domain.Department;

@Service
@Transactional(readOnly = false)
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Transactional(readOnly = false)
	@Override
	public void save(Department department) {
		departmentDao.save(department);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(Long id) {
		departmentDao.delete(id);
	}

	@Override
	public Department findById(Long id) {
		return departmentDao.findById(id);
	}

	@Override
	public List<Department> findAll() {
		return departmentDao.findAll();
	}

	@Override
	public boolean departmentHasRole(Long id) {
		if (findById(id).getRoles().isEmpty()) {
			return false;
		}
		return true;
	}

}
