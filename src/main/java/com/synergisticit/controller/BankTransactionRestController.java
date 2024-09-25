package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.repository.BankTransactionRepository;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.validation.BankTransactionValidatorDeposit;
import com.synergisticit.validation.BankTransactionValidatorTransfer;
import com.synergisticit.validation.BankTransactionValidatorWithdrawal;

import jakarta.validation.Valid;



@RestController
@RequestMapping("r2")
public class BankTransactionRestController {

	@Autowired
	BankTransactionRepository bankTransactionRepository;
	
	@RequestMapping("findAll")
	ResponseEntity<List<BankTransaction>> findAll(){
		return new ResponseEntity<List<BankTransaction>>(bankTransactionRepository.findAll(), HttpStatus.OK);
		
	}
	
	 @Autowired BankTransactionService bankTransactionService;
	    @Autowired AccountService accountService;
	    @Autowired BankTransactionValidatorDeposit bankTransactionValidatorDeposit;
	    @Autowired BankTransactionValidatorTransfer bankTransactionValidatorTransfer;
	    @Autowired BankTransactionValidatorWithdrawal bankTransactionValidatorWithdrawal;
	    
	    // http://localhost:8080/bankTransaction?bankTransactionId=1
	    @RequestMapping(value="bankTransaction", method=RequestMethod.GET)
	    public ResponseEntity<?> getBankTransactionById(@RequestParam Long bankTransactionId) {
	        BankTransaction bankTransaction = bankTransactionService.getBankTransactionById(bankTransactionId);
	        if (bankTransaction == null) {
	            return new ResponseEntity<String>("Transaction not found with id=" + bankTransactionId, HttpStatus.NOT_FOUND);
	        }
	        else {
	            return new ResponseEntity<BankTransaction>(bankTransaction, HttpStatus.FOUND);
	        }
	    }
	    
	    // http://localhost:8080/bankTransaction/all
	    @RequestMapping(value={"bankTransaction/all", "bankTransactions"}, method=RequestMethod.GET)
	    public ResponseEntity<List<BankTransaction>> getAllBankTransactions() {
	        List<BankTransaction> bankTransactions = bankTransactionService.getAllBankTransactions();
	        return new ResponseEntity<List<BankTransaction>>(bankTransactions, HttpStatus.FOUND);
	    }
	    
	    /*
	{
	    "bankTransactionId": 10,
	    "bankTransactionToAccount": 1,
	    "bankTransactionFromAccount": null,
	    "transactionAmount": 5.0,
	    "transactionType": "DEPOSIT",
	    "bankTransactionDateTime": "2023-08-16T20:26:00",
	    "comments": ""
	}
	     */
	    // http://localhost:8080/bankTransaction/deposit - save if id not already exist
	    @RequestMapping(value="bankTransaction/deposit", method=RequestMethod.POST)
	    public ResponseEntity<?> bankTransactionDeposit(@Valid @RequestBody BankTransaction bankTransaction, BindingResult br) {
	        bankTransactionValidatorDeposit.validate(bankTransaction, br);
	        
	        Long bankTransactionId = bankTransaction.getBankTransactionId();
	        if (bankTransactionService.existById(bankTransactionId)) {
	            return new ResponseEntity<String>("Bank Transaction already exists with id=" + bankTransactionId, HttpStatus.FOUND);
	        }
	        else if (br.hasFieldErrors()) {
	            String errorMessage = "Invalid input for following properties:\n";
	            for (FieldError f : br.getFieldErrors()) {
	                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
	            }
	            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
	        }
	        else {
	            Account toAccount = accountService.getAccount(bankTransaction.getBankTransactionToAccount());
	            double depositAmount = bankTransaction.getBankTransactionAmount();
	            toAccount.setAccountBalance(toAccount.getAccountBalance() + depositAmount);
	            accountService.createAccount(toAccount);
	            bankTransactionService.saveBankTransaction(bankTransaction);
	            return new ResponseEntity<BankTransaction>(bankTransaction, HttpStatus.CREATED);
	        }
	    }
	    
	    /*
	{
	    "bankTransactionId": 11,
	    "bankTransactionToAccount": null,
	    "bankTransactionFromAccount": 1,
	    "transactionAmount": 15.0,
	    "transactionType": "WITHDRAWAL",
	    "bankTransactionDateTime": "2023-08-17T18:53:00",
	    "comments": "Bill pay"
	}
	    */
	    // http://localhost:8080/bankTransaction/withdrawal - save if id not already exist
	    @RequestMapping(value="bankTransaction/withdrawal", method=RequestMethod.POST)
	    public ResponseEntity<?> bankTransactionWithdrawal(@Valid @RequestBody BankTransaction bankTransaction, BindingResult br) {
	        bankTransactionValidatorWithdrawal.validate(bankTransaction, br);
	        
	        Long bankTransactionId = bankTransaction.getBankTransactionId();
	        if (bankTransactionService.existById(bankTransactionId)) {
	            return new ResponseEntity<String>("Bank Transaction already exists with id=" + bankTransactionId, HttpStatus.FOUND);
	        }
	        else if (br.hasFieldErrors()) {
	            String errorMessage = "Invalid input for following properties:\n";
	            for (FieldError f : br.getFieldErrors()) {
	                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
	            }
	            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
	        }
	        else {
	            Account fromAccount = accountService.getAccount(bankTransaction.getBankTransactionFromAccount());
	            double withdrawalAmount = bankTransaction.getBankTransactionAmount();
	            fromAccount.setAccountBalance(fromAccount.getAccountBalance() - withdrawalAmount);  // validator checks for balance >= amount
	            accountService.createAccount(fromAccount);
	            bankTransactionService.saveBankTransaction(bankTransaction);
	            return new ResponseEntity<BankTransaction>(bankTransaction, HttpStatus.CREATED);
	        }
	    }
	    
	    /*
	{
	    "bankTransactionId": 12,
	    "bankTransactionToAccount": 2,
	    "bankTransactionFromAccount": 1,
	    "transactionAmount": 10.0,
	    "transactionType": "TRANSFER",
	    "bankTransactionDateTime": "2023-08-17T19:25:00",
	    "comments": "Here you go"
	}
	    */
	    // http://localhost:8080/bankTransaction/transfer - save if id not already exist
	    @RequestMapping(value="bankTransaction/transfer", method=RequestMethod.POST)
	    public ResponseEntity<?> bankTransactionTransfer(@Valid @RequestBody BankTransaction bankTransaction, BindingResult br) {
	        bankTransactionValidatorTransfer.validate(bankTransaction, br);
	        
	        Long bankTransactionId = bankTransaction.getBankTransactionId();
	        if (bankTransactionService.existById(bankTransactionId)) {
	            return new ResponseEntity<String>("Bank Transaction already exists with id=" + bankTransactionId, HttpStatus.FOUND);
	        }
	        else if (br.hasFieldErrors()) {
	            String errorMessage = "Invalid input for following properties:\n";
	            for (FieldError f : br.getFieldErrors()) {
	                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
	            }
	            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
	        }
	        else {
	            Account fromAccount = accountService.getAccount(bankTransaction.getBankTransactionFromAccount());
	            Account toAccount = accountService.getAccount(bankTransaction.getBankTransactionToAccount());
	            double transferAmount = bankTransaction.getBankTransactionAmount();
	            fromAccount.setAccountBalance(fromAccount.getAccountBalance() - transferAmount);  // validator checks for balance >= amount
	            toAccount.setAccountBalance(toAccount.getAccountBalance() + transferAmount);
	            accountService.createAccount(fromAccount);
	            accountService.createAccount(toAccount);
	            bankTransactionService.saveBankTransaction(bankTransaction);
	            return new ResponseEntity<BankTransaction>(bankTransaction, HttpStatus.CREATED);
	        }
	    }
	    
	    // transaction records are not to be updated or deleted
	
}
