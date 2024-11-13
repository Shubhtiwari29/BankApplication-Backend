package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Account;
import com.bank.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	//CREATE ACCOUNT
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account createAccount =  accountService.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
	}
	@GetMapping("/{accountNumber}")
	public Account getAccountDetailsByAccountNumber(@PathVariable Long accountNumber) {
		Account accountDetails = accountService.getAccountDetailsByAccountNumber(accountNumber);
		return accountDetails;
	}
	@GetMapping("/allaccounts")
	public List<Account> getAllAccountDetails(){
		List<Account> allAccounts = accountService.getAllAccountDetails();
		return allAccounts;
	}
	@PutMapping("/deposite/{accountNumber}/{amount}")
	public Account depositAmount(@PathVariable Long accountNumber,@PathVariable Double amount) {
		Account updatedAccount = accountService.depositAmount(accountNumber, amount);
		return updatedAccount;
	}
	@PutMapping("/withdraw/{accountNumber}/{amount}")
	public Account withDrawAmount(@PathVariable Long accountNumber, @PathVariable double amount) {
		Account updatedAccount = accountService.withDrawAmount(accountNumber, amount);
		return updatedAccount;
	}
	@DeleteMapping("/closeaccount/{accountNumber}")
	public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber)
	{
		accountService.closeAccount(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body("Account Closed");
	}
	
	
	
	
}