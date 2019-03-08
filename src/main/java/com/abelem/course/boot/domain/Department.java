package com.abelem.course.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "department")
public class Department extends AbastractEntity<Long> { // "id" vai ser do tipo Long
	
	@NotBlank(message = "Inform a name")
	@Size(min =3, max=60, message = "The name should have between {min} and {max} caracters")
	@Column (name = "name", nullable = false, unique = true, length = 60)
	private String name;
	
	@OneToMany(mappedBy = "department")
	private List<Role> roles;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
