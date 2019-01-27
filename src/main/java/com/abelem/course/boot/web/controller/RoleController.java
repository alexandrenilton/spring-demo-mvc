package com.abelem.course.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abelem.course.boot.domain.Department;
import com.abelem.course.boot.domain.Role;
import com.abelem.course.boot.service.DepartmentService;
import com.abelem.course.boot.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/create")
	public String create(Role role) {
		return "/roles/create";
	}
	
	@GetMapping("/list")
	public String list(ModelMap modelMap) {
		List<Role> roles = roleService.findAll();
		modelMap.addAttribute("roles", roles);
		return "/roles/list";
	}
	
	@PostMapping("/save") /*o BindingResult e @Valid, verificar se houve erro de validacoes */
	public String save(@Valid Role role, BindingResult bindingResult, RedirectAttributes attr) {
		
		if (bindingResult.hasErrors()) {
			return "/roles/create" ;
		}
		
		roleService.save(role);
		attr.addFlashAttribute("success", "Role successfully inserted");
		return "redirect:/roles/create";
	}
	
	@GetMapping("/update/{id}")
	public String preUpdate(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("role", roleService.findById(id));
		return "roles/create";
	}
	
	@PostMapping("/update")
	public String update(@Valid Role role, BindingResult bindingResult, RedirectAttributes attr) {
		
		if (bindingResult.hasErrors()) {
			return "/roles/create" ;
		}
		
		roleService.update(role);
		attr.addFlashAttribute("success", "Role has been updated!");
		return "redirect:/roles/create";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (roleService.roleHasEmployees(id)) {
			attr.addFlashAttribute("fail", "One or more employees linked to this Role");
		}else { 
			roleService.delete(id);
			attr.addFlashAttribute("success", "Role has been deleted");
		}
		
		return "redirect:/roles/list";
	}
	
	
	/** ModelAttributes **/
	
	/** la no html está como departments <option th:each="d : ${departments}" /> esse ${departments} vem aqui no método buscar os dados **/
	@ModelAttribute("departments") 
	public List<Department> departmentsList1() {
		return departmentService.findAll();
	}
}
