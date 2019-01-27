package com.abelem.course.boot.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abelem.course.boot.domain.Employee;
import com.abelem.course.boot.domain.Role;
import com.abelem.course.boot.domain.UF;
import com.abelem.course.boot.service.EmployeeService;
import com.abelem.course.boot.service.RoleService;
import com.abelem.course.boot.web.controller.util.Util;
import com.abelem.course.boot.web.validator.EmployeeValidator;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RoleService roleService;
	
	/** para validação do spring validator (messages em: message.properties **/
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new EmployeeValidator());
	}
	
	
	@GetMapping("/create")
	public String create(Employee employee) {
		return "/employees/create";
	}
	
	@GetMapping("/list")
	public String list(ModelMap modelMap) {
		List<Employee> employees = employeeService.findAll();
		modelMap.addAttribute("employees", employees);
		return "/employees/list";
	}
	
	@PostMapping("/save")
	public String save(@Valid Employee employee, BindingResult bindingResult, RedirectAttributes attr) {
		
		if (bindingResult.hasErrors()) {
			return "/employees/create" ;
		}
		
		employeeService.save(employee);
		attr.addFlashAttribute(Util.SUCCESS, "Employee has been saved");
		return "redirect:/employees/create";
	}
	
	@GetMapping("/update/{id}")
	public String preUpdate(@PathVariable("id") Long id, ModelMap modelMap) {
		Employee employee = employeeService.findById(id);
		modelMap.addAttribute("employee", employee);
		return "employees/create";
	}
	
	@PostMapping("/update")
	public String update(Employee employee, RedirectAttributes attr) {
		employeeService.update(employee);
		attr.addFlashAttribute(Util.SUCCESS, "Employee has been updated");
		return "redirect:/employees/create";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
		employeeService.delete(id);
		attr.addFlashAttribute(Util.SUCCESS, "Employee has been deleted");
		return "redirect:/employees/list";
	}
	
	
	/*
	 * Metodos finds por campos
	 */
	
	@GetMapping("/find/name")
	public String findByName(@RequestParam("name") String name, ModelMap modelMap) {
		modelMap.addAttribute("employees", employeeService.findByName(name));
		return "/employees/list";
	}
	
	@GetMapping("/find/role")
	public String findByRole(@RequestParam("id") Long roleId, ModelMap modelMap) {
		modelMap.addAttribute("employees", employeeService.findByRole(roleId));
		return "/employees/list";
	}
	
	@GetMapping("/find/date")
	public String findByDates(@RequestParam("in")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate in,
							  @RequestParam("out") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate out,
							  ModelMap modelMap) {
		modelMap.addAttribute("employees", employeeService.findByDates(in, out));
		return "/empoloyees/list";
	}
	
	/* 
	 * ModelAttributes 
	 * */
	@ModelAttribute("roles")
	public List<Role>  getRoles() {
		return roleService.findAll();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}
}

