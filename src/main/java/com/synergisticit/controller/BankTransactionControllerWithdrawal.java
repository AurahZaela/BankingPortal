package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.BankTransactionType;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.validation.BankTransactionValidatorDeposit;
import com.synergisticit.validation.BankTransactionValidatorWithdrawal;

import jakarta.validation.Valid;

@Controller
public class BankTransactionControllerWithdrawal {
    
    @Autowired 
    BankTransactionService bankTransactionService;
    
    @Autowired 
    AccountService accountService;
    
    @Autowired 
    BankTransactionValidatorWithdrawal bankTransactionValidatorWithdrawal;
    
    @RequestMapping("withdrawForm")
    public ModelAndView bankTransactionFormWithdrawalForm(BankTransaction bankTransaction) {
        ModelAndView modelAndView = getModelAndView();
        modelAndView.addObject("bankTransaction",bankTransaction);
        return modelAndView;
    }
    
    @RequestMapping("saveWithdraw")
    public ModelAndView saveBankTransaction(@Valid @ModelAttribute BankTransaction bankTransaction, BindingResult br) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
    	bankTransactionValidatorWithdrawal.validate(bankTransaction, br);
        ModelAndView modelAndView = getModelAndView();
        
        if (!br.hasErrors()) {  
        	System.out.println("Saving Withdrawl " + bankTransaction );
        	 Account fromAccount = accountService.getAccount(bankTransaction.getBankTransactionFromAccount());
             double withdrawalAmount = bankTransaction.getBankTransactionAmount();
             fromAccount.setAccountBalance(fromAccount.getAccountBalance() - withdrawalAmount);  // validator checks for balance >= amount
             accountService.createAccount(fromAccount);
             bankTransactionService.saveBankTransaction(bankTransaction);
            return modelAndView;
        }
        
        return new ModelAndView("redirect:/withdrawalForm");    // do not redirect, keep the info entered and show error messages
    }
    
    
    private ModelAndView getModelAndView() {
        ModelAndView modelAndView = new ModelAndView("bankTransactionFormWithdrawal");
        modelAndView.addObject("bankTransactions", bankTransactionService.getAllBankTransactions());
        modelAndView.addObject("accounts", accountService.getAccounts());
        modelAndView.addObject("banktransactionType", BankTransactionType.WITHDRAW);
        return modelAndView;
    }
}