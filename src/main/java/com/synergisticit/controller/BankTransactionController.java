package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.BankTransactionType;
import com.synergisticit.repository.BankTransactionRepository;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.validation.BankTransactionValidator;

import jakarta.validation.Valid;

@Controller
//@RequestMapping("bank")
public class BankTransactionController {
	@Autowired 
	BankTransactionService bankTransactionService;
	
    @Autowired 
    AccountService accountService;
    
    @Autowired 
    BankTransactionValidator bankTransactionValidator;
    
    @Autowired
    BankTransactionRepository bankTransactionRepository;
    
    
    @RequestMapping("bankTransactionForm")
    public ModelAndView bankTransactionForm(BankTransaction bankTransaction, @RequestParam int pageNo) {
    	ModelAndView modelAndView = getModelAndView();
    	
    	int pageSize = 5;
		String sortedBy="bankTransactionId";
        modelAndView.addObject("bankTransaction",bankTransaction);
       	
       	
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
        Page<BankTransaction> pageOfTransactions = bankTransactionRepository.findAll(pageable);
        List<BankTransaction> transactions = pageOfTransactions.getContent();
        modelAndView.addObject("totalPages", pageOfTransactions.getTotalPages());
        modelAndView.addObject("pageSize",pageSize);
        modelAndView.addObject("sortedBy",sortedBy);
        modelAndView.addObject("bankTransactions", transactions);
        
        return modelAndView;
    }
    
    
    @RequestMapping("saveBankTransaction")
    public ModelAndView saveBankTransaction(@Valid @ModelAttribute BankTransaction bankTransaction, BindingResult br) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        bankTransactionValidator.validate(bankTransaction, br);
        ModelAndView modelAndView = getModelAndView();
        if (br.hasErrors()) {
        	modelAndView.addObject("hasErrors", br.hasErrors());
        	List<BankTransaction> bankTransactions = bankTransactionService.getAllBankTransactions();
        	modelAndView.addObject("bankTransactions", bankTransactions);
        	return modelAndView;
        }
        	else {
            Account fromAccount = accountService.getAccount(bankTransaction.getBankTransactionFromAccount());
            Account toAccount = accountService.getAccount(bankTransaction.getBankTransactionToAccount());
            bankTransactionService.saveBankTransaction(bankTransaction);
            return new ModelAndView("redirect:/bankTransactionForm");  
            }
        
    }
    
    private ModelAndView getModelAndView() {

        ModelAndView modelAndView = new ModelAndView("bankTransactionForm");
        modelAndView.addObject("accounts", accountService.getAccounts());
        modelAndView.addObject("banktransactionType", BankTransactionType.values());
        return modelAndView;
    }
    
    @RequestMapping(value = "deleteBankTransaction")
    public ModelAndView deleteBankTransaction(@RequestParam Long bankTransactionId) {
    	bankTransactionService.deleteBankTransactionById(bankTransactionId);
       
        return new ModelAndView("redirect:/bankTransactionForm");
    }
    
    @RequestMapping(value = "updateBankTransaction")
    public ModelAndView updateBankTransaction(BankTransaction bankTransaction) {
    	ModelAndView modelAndView = getModelAndView();
        BankTransaction bankTransactionUpdate = bankTransactionService.getBankTransactionById(bankTransaction.getBankTransactionId());
        modelAndView.addObject("bankTransaction", bankTransactionUpdate);
        return modelAndView;
    }
    
    
}   