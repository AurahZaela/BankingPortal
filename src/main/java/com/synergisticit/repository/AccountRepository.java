package com.synergisticit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Account;


public interface AccountRepository extends JpaRepository<Account,Long> {

	@Query(value = "SELECT accountId FROM account ORDER BY accountId DESC LIMIT 1", nativeQuery = true)
    Long getNextSeriesId();
	
	Page<Account> findAll(Pageable pageable);
}
