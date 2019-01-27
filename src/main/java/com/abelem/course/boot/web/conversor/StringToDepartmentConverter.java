package com.abelem.course.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.abelem.course.boot.domain.Department;
import com.abelem.course.boot.service.DepartmentService;

@Component /** para que ele fique gerenciavel pelo Spring **/
public class StringToDepartmentConverter implements Converter<String, Department>{
	
	@Autowired
	private DepartmentService service;
	
	@Override
	public Department convert(String text) {
		if (text.isEmpty()) {
			return null;
		} else {
			Long id = Long.valueOf(text);
			return service.findById(id);
		}
	}
}
