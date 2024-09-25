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

import jakarta.validation.Valid;

@Controller
public class BankTransactionControllerDeposit {
    
    @Autowired BankTransactionService bankTransactionService;
    @Autowired AccountService accountService;
    @Autowired BankTransactionValidatorDeposit bankTransactionValidatorDeposit;
    
    @RequestMapping("depositForm")
    public ModelAndView bankTransactionFormDepositForm(BankTransaction bankTransaction) {
        ModelAndView modelAndView = getModelAndView();
        modelAndView.addObject("bankTransaction",bankTransaction);
        return modelAndView;
    }
    
    @RequestMapping("saveDeposit")
    public ModelAndView saveBankTransaction(@Valid @ModelAttribute BankTransaction bankTransaction, BindingResult br) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        bankTransactionValidatorDeposit.validate(bankTransaction, br);
        ModelAndView modelAndView = getModelAndView();
        
        if (!br.hasErrors()) {  // update account for deposit and save transaction for record.
        	System.out.println("Saving Deposit " + bankTransaction );
            Account toAccount = accountService.getAccount(bankTransaction.getBankTransactionToAccount());
            double depositAmount = bankTransaction.getBankTransactionAmount();
            toAccount.setAccountBalance(toAccount.getAccountBalance() + depositAmount);
            accountService.createAccount(toAccount);
            bankTransactionService.saveBankTransaction(bankTransaction);
            return modelAndView;
        }
        
        return new ModelAndView("redirect:/depositForm");    // do not redirect, keep the info entered and show error messages
    }
    
    
    private ModelAndView getModelAndView() {
        ModelAndView modelAndView = new ModelAndView("bankTransactionFormDeposit");
        modelAndView.addObject("bankTransactions", bankTransactionService.getAllBankTransactions());
        modelAndView.addObject("accounts", accountService.getAccounts());
        modelAndView.addObject("banktransactionType", BankTransactionType.values());
        return modelAndView;
    }
}