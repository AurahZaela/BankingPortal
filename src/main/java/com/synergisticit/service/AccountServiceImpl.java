package com.synergisticit.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Account;
import com.synergisticit.repository.AccountRepository;

@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void deleteAccount(Long accountId) {
		accountRepository.deleteById(accountId);

	}

	@Override
	public Account getAccount(Long accountId) {
		Optional<Account> optAccount = accountRepository.findById(accountId);
		
		if(optAccount.isPresent()) 
			return optAccount.get();
		else 
			return null;
		
	}

	@Override
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public boolean existById(Long accountId) {
		return accountRepository.existsById(accountId);
	}

	@Override
	public Long getNextBranchId() {
		return accountRepository.getNextSeriesId();
	}

}
