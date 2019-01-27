package com.abelem.course.boot.web.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import com.abelem.course.boot.web.controller.util.Util;

public class MyErrorView implements ErrorViewResolver{

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		
		//model.forEach((k,v) -> System.out.println(k + ":" + v + "\n"));
		
		ModelAndView model = new ModelAndView("/error");
		model.addObject("status", status.value());
		
		switch(status.value()) {
			case 404:
				model.addObject(Util.ERROR, "Página não encontrada.");
				model.addObject(Util.MESSAGE, "A url para a página "+ map.get("path") +"' não existe.");
				break;
			
			case 500:
				model.addObject(Util.ERROR, "Ocorreu um erro interno no servidor.");
				model.addObject(Util.MESSAGE, "Ocorreu um erro inesperado, tente mais tarde.");
				break;
				
			default:
				model.addObject(Util.ERROR, map.get("error"));
				model.addObject(Util.MESSAGE, map.get("message"));
				break;
		}
		
		return model;
	}

}
