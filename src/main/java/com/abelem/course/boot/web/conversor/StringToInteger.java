package com.abelem.course.boot.web.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToInteger implements Converter<String, Integer> {

	@Override
	public Integer convert(String text) {
		text = text.trim();
		
		
		/** expressão regular **/
		if (text.matches("[0-9]+")) {
			return Integer.parseInt(text);
		}
		return null;
	}
	
	
}
