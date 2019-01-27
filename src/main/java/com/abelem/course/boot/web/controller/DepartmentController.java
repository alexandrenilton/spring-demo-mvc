package com.abelem.course.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abelem.course.boot.domain.Department;
import com.abelem.course.boot.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@GetMapping("/create")
	public String create(Department department) {
		return "/departments/create"; /* templates/departments/create.html */
	}
	
	@GetMapping("/list")
	public String list(ModelMap modelMap) {
		List<Department> departments = service.findAll();
		modelMap.addAttribute("departments", departments);
		return "/departments/list";
	}
	
	@PostMapping("/save")
	public String save(@Valid Department department, BindingResult bindingResult, RedirectAttributes attr) {
		
		if (bindingResult.hasErrors()) {
			return "/departments/create" ;
		}
		
		service.save(department);
		attr.addFlashAttribute("success", "Department has been saved"); //acao de redirect, tem que usar RedirectAttribute ao inves de ModalMap
		return "redirect:/departments/create";
	}
	
	@GetMapping("/update/{id}")
	public String preUpdate(@PathVariable("id") Long id, ModelMap modelMap) {
		Department department = service.findById(id);
		modelMap.addAttribute("department", department);
		return "/departments/create";
	}
	
	@PostMapping("/update")
	public String update(@Valid Department department, BindingResult bindingResult, RedirectAttributes attr) {
		
		if (bindingResult.hasErrors()) {
			return "/departments/create" ;
		}
		
		
		service.update(department);
		attr.addFlashAttribute("success", "Department has been updated!");
		return "redirect:/departments/create";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, ModelMap modelMap) {
		if ( ! service.departmentHasRole(id) ) {
			service.delete(id);
			modelMap.addAttribute("success", "Department has been removed!");
		} else {
			modelMap.addAttribute("fail", "Department hasn't been removed! There are some roles attributed to this department.");
		}
		
		return list(modelMap); //similar ao redirect
	}

}
