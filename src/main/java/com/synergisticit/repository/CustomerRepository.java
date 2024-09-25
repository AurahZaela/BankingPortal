package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Query(value = "SELECT customerId FROM customer ORDER BY customerId DESC LIMIT 1", nativeQuery = true)
    Long getNextSeriesId();
}
