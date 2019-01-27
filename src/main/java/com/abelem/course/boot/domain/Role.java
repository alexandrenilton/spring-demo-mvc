package com.abelem.course.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "role")
public class Role extends AbastractEntity<Long> {
	
	
	@NotBlank(message = "The Role name is required")
	@Size(max = 60, message = "The role name should have max 60 caracters")
	@Column(name = "name", nullable = false, unique = true , length = 60)
	private String name;
	
	@NotNull(message = "Select the departmet.")
	@ManyToOne
	@JoinColumn(name = " id_department_fk")
	private Department department;
	
	@OneToMany(mappedBy = "role")
	private List<Employee> employees;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}
