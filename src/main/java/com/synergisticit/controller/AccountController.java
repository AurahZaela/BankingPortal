package com.synergisticit.controller;

import org.springframework.data.domain.Page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.synergisticit.domain.AccountType;
import com.synergisticit.domain.Branch;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BranchService;
import com.synergisticit.service.CustomerService;
import com.synergisticit.repository.AccountRepository;
import com.synergisticit.validation.AccountValidator;

import jakarta.validation.Valid;

@Controller
//@RequestMapping("account") not needed too confusing to switch between forms this way
public class AccountController {
	
	@Autowired
	BranchService branchService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountValidator accountValidator;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AccountRepository accountRepository;
	
	long nextAccId=1;
	
	@RequestMapping("accountForm")
    public ModelAndView accountForm(Account account, @RequestParam int pageNo) {
		
		if(accountService.getNextBranchId() != null) {
			nextAccId = accountService.getNextBranchId() +1 ;
		}
		
		account.setAccountId(nextAccId);
        ModelAndView modelAndView = getModelAndView();
        
        modelAndView.addObject("accountTypes", AccountType.values());
        modelAndView.addObject("branches", branchService.findAll());
        modelAndView.addObject("customers", customerService.findAll());
        
        int pageSize = 5;
		String sortedBy="accountId";
		modelAndView.addObject("account", account);
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
        Page<Account> pageOfAccounts = accountRepository.findAll(pageable);
        List<Account> accounts = pageOfAccounts.getContent();
        
        modelAndView.addObject("totalPages", pageOfAccounts.getTotalPages());
        modelAndView.addObject("pageSize",pageSize);
        modelAndView.addObject("sortedBy",sortedBy);
        modelAndView.addObject("accounts", accounts);
        return modelAndView;
    }
	
	 public ModelAndView getModelAndView(){
		 
	        ModelAndView modelAndView = new ModelAndView("accountForm");
//	        
//	        modelAndView.addObject("accountTypes", AccountType.values());
//	        modelAndView.addObject("branches", branchService.findAll());
//	        modelAndView.addObject("customers", customerService.findAll());
	        return modelAndView;
	    }
	 
	 @RequestMapping("saveAccount")
	    public ModelAndView addAccount(@Valid @ModelAttribute Account account, BindingResult br) {
		 ModelAndView modelAndView = getModelAndView();
		 accountValidator.validate(account, br);
		 
		 
		 if(br.hasErrors()) {
	            modelAndView.addObject("hasErrors", br.hasErrors());
	            List<Account> accounts = accountService.getAccounts();
	            modelAndView.addObject("accounts", accounts);
	            return modelAndView;
	        } else {
	        	accountService.createAccount(account);
	            if(accountService.getNextBranchId() != null){
	            	nextAccId = accountService.getNextBranchId() + 1;
	            }
	            return new ModelAndView("redirect:/accountForm?pageNo=0");
	        }
	 }
}
