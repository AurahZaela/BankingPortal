package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Customer;
import com.synergisticit.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(long customerId) {
Optional<Customer> optCust = customerRepository.findById(customerId);
		
		if(optCust.isPresent()) {
			return optCust.get();
		}
		else {
		return null;
		}
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public void deleteById(long customerId) {
		customerRepository.deleteById(customerId);

	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Long getNextCustomerId() {
		return customerRepository.getNextSeriesId();
	}

	@Override
	public boolean existsById(long customerId) {
		return customerRepository.existsById(customerId);
	}

}
