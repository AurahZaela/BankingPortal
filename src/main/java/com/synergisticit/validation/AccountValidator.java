package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Account;

@Component
public class AccountValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountHolder", "account.accountHolder.value", "must have account holder");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountType", "account.accountType.value", "must select an account type");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountDateOpened", "account.accountDateOpened.value", "must have date open");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountBalance", "account.accountBalance.value", "must have balance");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountBranch", "account.accountBranch.value", "must select a branch");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountCustomer", "account.accountCustomer.value", "must select a customer");
        
        Account account = (Account) target;
        if (account.getAccountBalance() <= 0) {
            errors.rejectValue("accountBalance", "account.accountBalance.range", "balance should be greater than 0");
        }
	}

}
