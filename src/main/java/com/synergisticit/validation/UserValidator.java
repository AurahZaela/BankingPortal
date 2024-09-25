package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.User;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		 User user = (User) target;
	        if(user.getRoles().isEmpty()){
	            errors.rejectValue("roles", "role.empty", "Roles cannot be empty");
	        }
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Username cannot be empty");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password cannot be empty");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Email cannot be empty");
	}

}
