package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.BankTransaction;
import com.synergisticit.repository.BankTransactionRepository;

@Service
public class BankTransactionServiceImpl implements BankTransactionService{

	@Autowired
	BankTransactionRepository bankTransactionRepository;
	
	@Override
	public BankTransaction saveBankTransaction(BankTransaction bankTransaction) {
		return bankTransactionRepository.save(bankTransaction);
	}

	@Override
	public BankTransaction getBankTransactionById(Long bankTransactionId) {
		Optional<BankTransaction> opt = bankTransactionRepository.findById(bankTransactionId);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
	}

	@Override
	public List<BankTransaction> getAllBankTransactions() {
		return bankTransactionRepository.findAll();
	}

	@Override
	public void deleteBankTransactionById(Long bankTransactionId) {
		bankTransactionRepository.deleteById(bankTransactionId);
	}

	@Override
	public boolean existById(Long bankTransactionId) {
		
		return bankTransactionRepository.existsById(bankTransactionId);
	}

}
