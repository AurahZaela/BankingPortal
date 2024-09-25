package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.BankTransaction;

@Component
public class BankTransactionValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return BankTransaction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionToAccount", "bankTransaction.bankTransactionToAccount.value", "must have account to");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionFromAccount", "bankTransaction.bankTransactionFromAccount.value", "must have account from");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionAmount", "bankTransaction.bankTransactionAmount.value", "must have transaction amount");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "banktransactionType", "bankTransaction.banktransactionType.value", "must select a transaction type");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionDateTime", "bankTransaction.bankTransactionDateTime.value", "must have date and time");
        
        BankTransaction bankTransaction = (BankTransaction) target;
        if (bankTransaction.getBankTransactionAmount() <= 0) {
            errors.rejectValue("transactionAmount", "bankTransaction.transactionAmount.range", "transaction amount should be greater than 0");
        }

	}

}
