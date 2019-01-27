package com.abelem.course.boot.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.abelem.course.boot.domain.AddressBr;
import com.abelem.course.boot.domain.Employee;

@Repository
public class EmployeeDaoImpl extends AbastractDao<Employee, Long> implements EmployeeDao {
	
	
	@Override
	public List<Employee> findByName(String name) {
		/*
		TypedQuery<Employee> query = getEntityManager()
				.createQuery("select emp from Employee emp where emp.name like :name", Employee.class);
		query.setParameter("name", name);
		
		return query.getResultList(); */
		
		
		return createQuery("select e from Employee e where e.name like concat('%', ?1, '%')", name);
	}

	@Override
	public List<Employee> findByRole(Long roleId) {
		return createQuery("select e from Employee e where e.role.id = ?1", roleId);
	}

	@Override
	public List<Employee> findByDateInDateOut(LocalDate in, LocalDate out) {
		String jpql = new StringBuilder("select e from Employee e ")
				.append("where e.dataIn >= ?1 and e.dataSaida <= ?2 ")
				.append(" order by e.dataIn asc ")
				.toString();
		return createQuery(jpql, in, out);
	}

	@Override
	public List<Employee> findByDataIn(LocalDate in) {
		String jpql = new StringBuilder("select e from Employee e ")
				.append("where e.dataIn = ?1 ")
				.append(" order by e.dataIn asc ")
				.toString();
		return createQuery(jpql, in);
	}

	@Override
	public List<Employee> findByDataOut(LocalDate out) {
		String jpql = new StringBuilder("select e from Employee e ")
				.append("where e.dataOut = ?1 ")
				.append(" order by e.dataIn asc ")
				.toString();
		return createQuery(jpql, out);
	}


	
	

}
