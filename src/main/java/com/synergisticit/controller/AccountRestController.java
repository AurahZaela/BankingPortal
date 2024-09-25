package com.synergisticit.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Account;
import com.synergisticit.repository.AccountRepository;
import com.synergisticit.service.AccountService;
import com.synergisticit.validation.AccountValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("r1")
public class AccountRestController {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountValidator accountValidator;
	
	JdbcTemplate jdbcTemplate;
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@RequestMapping("findAll")
	ResponseEntity<List<Account>> findAll(){
		
		return new ResponseEntity<List<Account>>(accountRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="account/create", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAccount(@Valid @RequestBody Account account, BindingResult br) {
        accountValidator.validate(account, br);
        
        Long accountId = account.getAccountId();
        if (accountService.existById(accountId)) {
            return new ResponseEntity<String>("Account already exists with id=" + accountId, HttpStatus.FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            accountService.createAccount(account);
            return new ResponseEntity<Account>(account, HttpStatus.CREATED);
        }
    }
	 /*
	{
	    "accountId": 1,
	    "accountHolder": "Potato",
	    "accountType": "SAVINGS",
	    "accountDateOpen": "2023-08-10",
	    "accountBalance": 1040.0,
	    "accountBranch": {
	        "branchId": 1
	    }
	}
	     */
	
    // http://localhost:8080/account/update - save if id already exists
    @RequestMapping(value="account/update", method=RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@Valid @RequestBody Account account, BindingResult br) {
        accountValidator.validate(account, br);
        
        Long accountId = account.getAccountId();
        if (!accountService.existById(accountId)) {
            return new ResponseEntity<String>("Account does not exist with id=" + accountId, HttpStatus.NOT_FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            accountService.createAccount(account);
            return new ResponseEntity<Account>(account, HttpStatus.ACCEPTED);
        }
    }
    
    // http://localhost:8080/account/delete?accountId=11
    @RequestMapping(value="account/delete", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccountById(@RequestParam Long accountId) {
        if (accountService.existById(accountId)) {
            accountService.deleteAccount(accountId);
            return new ResponseEntity<String>("Account deleted with id=" + accountId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("Account does not exist with id=" + accountId, HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping("findAllWithJDBC")
	public List<Account> findAll2(){
    	
		String sql = "select * from account";
		return jdbcTemplate.query(sql, new RowMapper<Account>(){

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				int accId = rs.getInt("accountId");
				Account a = new Account();
				
				return a;
			}});
	}
	
}
