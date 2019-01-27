package com.abelem.course.boot.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


@Entity
@Table (name = "employees")
@SuppressWarnings("serial")
public class Employee extends AbastractEntity<Long> {
	
	@NotBlank
	@Size(max = 255, min =3)
	@Column(nullable = false, unique = true)
	private String name;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00") /*dispensa uso de converter para passar do HTML para o Controller*/
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal wage;
	
	@NotNull
	@PastOrPresent(message = "{PastOrPresent.employee.dataIn}")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "date_in", nullable = false, columnDefinition = "DATE")
	private LocalDate dateIn;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "date_out", columnDefinition = "DATE")
	private LocalDate dateOut;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id_fk")
	private AddressBr addressBr;
	
	@NotNull(message = "{NotNull.employee.role}")
	@ManyToOne
	@JoinColumn(name = "role_id_fk")
	private Role role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getWage() {
		return wage;
	}

	public void setWage(BigDecimal wage) {
		this.wage = wage;
	}

	public LocalDate getDateIn() {
		return dateIn;
	}

	public void setDateIn(LocalDate dateIn) {
		this.dateIn = dateIn;
	}

	public LocalDate getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}

	public AddressBr getAddressBr() {
		return addressBr;
	}

	public void setAddressBr(AddressBr addressBr) {
		this.addressBr = addressBr;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
