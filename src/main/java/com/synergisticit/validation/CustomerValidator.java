package com.synergisticit.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Customer;

@Component
public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
Customer c = (Customer)target;
		
		if(c.getCustomerGender() == null){
            errors.rejectValue("customerGender", "customerGender.empty", "Gender is required");
        }
        if(c.getCustomerDOB() == null){
            errors.rejectValue("customerDOB", "customerDOB.empty", "Date of Birth is required");
        }

		ValidationUtils.rejectIfEmpty(errors, "customerName", "customerName.empty", "Customer name is required");
        ValidationUtils.rejectIfEmpty(errors, "customerSSN", "customerSSN.empty", "SSN is required");
        ValidationUtils.rejectIfEmpty(errors, "customerMobileNo", "customerMobileNo.empty", "Mobile number is required");
        ValidationUtils.rejectIfEmpty(errors, "customerAddress.addressLine1", "customerAddress.addressLine1.empty", "Address is required");
        ValidationUtils.rejectIfEmpty(errors, "customerAddress.city", "customerAddress.city.empty", "City is required");
        ValidationUtils.rejectIfEmpty(errors, "customerAddress.state", "customerAddress.state.empty", "State is required");
        ValidationUtils.rejectIfEmpty(errors, "customerAddress.zip", "customerAddress.zip.empty", "Zip code is required");
        ValidationUtils.rejectIfEmpty(errors, "customerAddress.country", "customerAddress.country.empty", "Country is required");


	}

}
