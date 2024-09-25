package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;
import com.synergisticit.validation.RoleValidator;

@Controller
//@RequestMapping("role")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@Autowired
	RoleValidator roleValidator;
	
	long nextRoleId=1;
	
	@RequestMapping("roleForm")
	public ModelAndView roleForm(Role role) {
		if(roleService.getNextRoleId() != null) {
			nextRoleId = roleService.getNextRoleId() +1;
		}
		role.setRoleId(nextRoleId);
		ModelAndView modelAndView=getModelAndView();
		modelAndView.addObject("role", role);
		return modelAndView;
	}
	
	 private ModelAndView getModelAndView(){
	        ModelAndView modelAndView = new ModelAndView("roleForm");
	        List<Role> roles = roleService.findAll();
	        modelAndView.addObject("roles", roles);
	        return modelAndView;
	    }
	
	
	@RequestMapping("saveRole")
	public ModelAndView saveRole(@Validated @ModelAttribute Role role, BindingResult bindingResult) {
		roleValidator.validate(role, bindingResult);
		ModelAndView modelAndView = getModelAndView();
		
		if(bindingResult.hasErrors()) {
            modelAndView.addObject("hasErrors", bindingResult.hasErrors());
            List<Role> roles = roleService.findAll();
            modelAndView.addObject("roles",roles);
            return modelAndView;
		}
		else {
			roleService.save(role);
			return new ModelAndView("redirect:roleForm");
			}
	}
	
	
	@RequestMapping("updateRole")
	public ModelAndView updateRole(Role role) {
		Role roleToUpdate = roleService.findByID(role.getRoleId());
		ModelAndView modelAndView = getModelAndView();
		modelAndView.addObject("role",roleToUpdate);
		return modelAndView;
	}
	
	@RequestMapping("deleteRole")
	public ModelAndView deleteRole(Role role) {
		roleService.deleteByID(role.getRoleId());
		return new ModelAndView("redirect:/role/");
	}
	
}
	
