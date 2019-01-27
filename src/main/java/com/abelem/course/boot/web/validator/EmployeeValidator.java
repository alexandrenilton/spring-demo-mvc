package com.abelem.course.boot.web.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.abelem.course.boot.domain.Employee;

public class EmployeeValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Employee e = (Employee) object;
		
		LocalDate dataIn = e.getDateIn();
		LocalDate dataOut = e.getDateOut();
		
		if ( dataOut != null) {
			if (dataOut.isBefore(dataIn) ) {
				errors.rejectValue("dataOut", "PostDataIn.employee.dataOut");
			}
		}
	}

}
