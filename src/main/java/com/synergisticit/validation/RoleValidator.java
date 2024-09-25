package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Role;

@Component
public class RoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Role.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "role.roleId.value", "must have role ID");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "role.roleName.value", "must have role name");

	}

}
