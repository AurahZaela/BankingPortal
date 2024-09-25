package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.BankTransaction;

public interface BankTransactionService {
	BankTransaction saveBankTransaction(BankTransaction bankTransaction);
    BankTransaction getBankTransactionById(Long bankTransactionId);
    List<BankTransaction> getAllBankTransactions();
    void deleteBankTransactionById(Long bankTransactionId);
    boolean existById(Long bankTransactionId);
}
