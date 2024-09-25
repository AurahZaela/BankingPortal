package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.UserValidator;

@Controller
//@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired 
	RoleService roleService;
	
	@Autowired
	UserValidator userValidator;
	
	long nextUserId=1;
	
	@RequestMapping("userForm")
    public ModelAndView userForm(User user) {
		if(userService.getNextUserId() != null) {
			nextUserId = userService.getNextUserId() +1;
		}
		user.setUserId(nextUserId);
        ModelAndView modelAndView = getModelAndView();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("saveUser")
    public ModelAndView saveUser(@Validated @ModelAttribute User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        ModelAndView modelAndView = getModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("hasErrors", bindingResult.hasErrors());
            List<User> users = userService.findAll();
            modelAndView.addObject("users", users);
            return modelAndView;
        } else {
            userService.save(user);
            return new ModelAndView("redirect:userForm");
        }
    }
    
    @RequestMapping("updateUser")
	public String forUpdatingTheUser(User user, Model model) {
		
		User retrievedUser = userService.findById(user.getUserId());
		List<Role> retriedRoles = retrievedUser.getRoles();
		model.addAttribute("users", userService.findAll());
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("u", retrievedUser);
		model.addAttribute("retriedRoles", retriedRoles);
		return "userForm";
	}

    private ModelAndView getModelAndView() {
        ModelAndView modelAndView = new ModelAndView("userForm");
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("roles", roleService.findAll());
        return modelAndView;
    }
}
