package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Branch;

@Component
public class BranchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Branch.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Branch branch = (Branch) target;
        ValidationUtils.rejectIfEmpty(errors, "branchName", "branchName.empty", "Branch name is required");
        ValidationUtils.rejectIfEmpty(errors, "branchAddress.addressLine1", "branchAddress.addressLine1.empty", "Address is required");
        ValidationUtils.rejectIfEmpty(errors, "branchAddress.city", "branchAddress.city.empty", "City is required");
        ValidationUtils.rejectIfEmpty(errors, "branchAddress.state", "branchAddress.state.empty", "State is required");
        ValidationUtils.rejectIfEmpty(errors, "branchAddress.zip", "branchAddress.zip.empty", "Zip code is required");
        ValidationUtils.rejectIfEmpty(errors, "branchAddress.country", "branchAddress.country.empty", "Country is required");

	}

}
