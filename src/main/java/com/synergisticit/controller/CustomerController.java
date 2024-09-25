package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Gender;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.AddressValidator;
import com.synergisticit.validation.CustomerValidator;

@Controller
//@RequestMapping("customer")
public class CustomerController {
	 @Autowired
	    CustomerService customerService;
	    @Autowired
	    AccountService accountService;
	    @Autowired
	    CustomerValidator customerValidator;
	    @Autowired
	    AddressValidator addressValidator;
	    @Autowired
	    UserService userService;
	    
	    
	    long nextCustomerId = 1;

	    @RequestMapping("customerForm")
	    public ModelAndView customerForm(Customer customer) {
	        if(customerService.getNextCustomerId() != null){
	            nextCustomerId = customerService.getNextCustomerId() +1;
	        }
	        customer.setCustomerId(nextCustomerId);
	        ModelAndView modelAndView = getModelAndView();
	        modelAndView.addObject("customer", customer);
	        return modelAndView;
	    }

	    @RequestMapping(value = "saveCustomer")
	    public ModelAndView addCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult) {
	        customerValidator.validate(customer, bindingResult);
	        addressValidator.validate(customer.getCustomerAddress(), bindingResult);
	        ModelAndView modelAndView = getModelAndView();
	        if (bindingResult.hasErrors()) {
	            modelAndView.addObject("hasErrors", bindingResult.hasErrors());
	            List<Customer> customers = customerService.findAll();
	            modelAndView.addObject("customers", customers);
	            return modelAndView;
	        } else {
	            customerService.save(customer);
	            if(customerService.getNextCustomerId() != null){
	                nextCustomerId = customerService.getNextCustomerId()+1;
	            }
	            return new ModelAndView("redirect:/customerForm");
	        }

	    }

	    @RequestMapping(value = "getCustomerById")
	    public ModelAndView getCustomerById(Long id) {
	        ModelAndView modelAndView = new ModelAndView();
	        Customer customer = customerService.findById(id);
	        modelAndView.addObject("customer", customer);
	        //modelAndView.setViewName("customers");
	        return modelAndView;
	    }

	    @RequestMapping(value = "updateCustomer")
	    public ModelAndView updateCustomer(Customer customer) {
	        ModelAndView modelAndView = getModelAndView();
	        Customer customerToUpdate = customerService.findById(customer.getCustomerId());
	        List<Account> retrievedAccounts = customerToUpdate.getCustomerAccounts();
	        modelAndView.addObject("customer", customerToUpdate);
	        modelAndView.addObject("retrievedAccounts", retrievedAccounts);
	        modelAndView.addObject("accounts", accountService.getAccounts());
	        //modelAndView.setViewName("customers");
	        return modelAndView;
	    }
	    @RequestMapping(value = "deleteCustomer")
	    public ModelAndView deleteCustomer(@RequestParam Long customerId) {
	        customerService.deleteById(customerId);
	        return new ModelAndView("redirect:/customerForm");
	    }
	    private ModelAndView getModelAndView() {
	        ModelAndView modelAndView = new ModelAndView("customerForm");
	        List<Customer> customers = customerService.findAll();
	        List<Account> accounts = accountService.getAccounts();
	        modelAndView.addObject("customers", customers);
	        modelAndView.addObject("accounts", accounts);
	        modelAndView.addObject("genders", Gender.values());
	        return modelAndView;
	    }
		
}
