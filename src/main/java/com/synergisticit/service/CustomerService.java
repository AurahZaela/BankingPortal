package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Customer;

public interface CustomerService {
	Customer save(Customer customer);
	Customer findById(long customerId);
	List<Customer> findAll();
	void deleteById(long customerId);
	Customer updateCustomer(Customer customer);
	Long getNextCustomerId();
	
	boolean existsById(long customerId);
}
