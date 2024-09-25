package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Account;

public interface AccountService {
	Account createAccount(Account account);
    Account updateAccount(Account account);
    void deleteAccount(Long accountId);
    Account getAccount(Long accountId);
    List<Account> getAccounts();
    boolean existById(Long accountId);
    
    Long getNextBranchId();
}
