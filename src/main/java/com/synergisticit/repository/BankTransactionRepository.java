package com.synergisticit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {
	Page<BankTransaction> findAll(Pageable pageable);
}
