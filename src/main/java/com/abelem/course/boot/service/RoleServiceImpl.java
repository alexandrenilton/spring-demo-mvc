package com.abelem.course.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abelem.course.boot.dao.RoleDao;
import com.abelem.course.boot.domain.Role;


@Service
@Transactional (readOnly = false) /* false é o valor padrão*/
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void delete(Long id) {
		roleDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Role findById(Long id) {
		return roleDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public boolean roleHasEmployees(Long id) {
		if (findById(id).getEmployees().isEmpty()) {
			return false;
		}
		return true;
	}

}
