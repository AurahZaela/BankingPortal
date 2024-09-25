package com.synergisticit.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.service.AccountService;

@Component
public class BankTransactionValidatorTransfer implements Validator {

	@Autowired 
	AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return BankTransaction.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionFromAccount", "bankTransaction.bankTransactionFromAccount.value", "must have account from");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionToAccount", "bankTransaction.bankTransactionToAccount.value", "must have account to");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionAmount", "bankTransaction.bankTransactionAmount.value", "must have transaction amount");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionType", "bankTransaction.banktransactionType.value", "must select a transaction type");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionDateTime", "bankTransaction.bankTransactionDateTime.value", "must have date and time");
        
        BankTransaction bankTransaction = (BankTransaction) target;
        double withdrawalAmount = bankTransaction.getBankTransactionAmount();
        
        if (withdrawalAmount <= 0) {
            errors.rejectValue("bankTransactionAmount", "bankTransaction.bankTransactionAmount.range", "transaction amount should be greater than 0");
        }
        
        Account fromAccount = accountService.getAccount(bankTransaction.getBankTransactionFromAccount());
        double balance = fromAccount.getAccountBalance();
        if (balance < withdrawalAmount) {
            errors.rejectValue("transactionAmount", "bankTransaction.transactionAmount.balance", "requested transfer amount is more than balance ($ " + balance + ")");
        }
        
        if (bankTransaction.getBankTransactionFromAccount() == bankTransaction.getBankTransactionToAccount()) {
            errors.rejectValue("bankTransactionToAccount", "bankTransaction.bankTransactionToAccount.equals", "must transfer to different account");
        }
        
    }


}
