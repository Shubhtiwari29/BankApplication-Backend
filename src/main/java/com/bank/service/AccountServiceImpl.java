package com.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.Account;
import com.bank.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account createAccount(Account account) {
		Account saved_account = accountRepository.save(account);
		return saved_account;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accuntNumber) {
		Optional<Account> account = accountRepository.findById(accuntNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountDetils = account.get();
		return accountDetils;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> listOfAccounts = accountRepository.findAll();
		return listOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		Optional<Account> account = accountRepository.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountPresent = account.get();
		Double totalBalance = accountPresent.getAccount_balance()+amount;
		accountPresent.setAccount_balance(totalBalance);
		accountRepository.save(accountPresent);
		return accountPresent;
	}

	@Override
	public Account withDrawAmount(Long accountNumber, Double amount) {
		Optional<Account> account = accountRepository.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountPresent = account.get();
		Double totalBalance = accountPresent.getAccount_balance()-amount;
		accountPresent.setAccount_balance(totalBalance);
		accountRepository.save(accountPresent);
		return accountPresent;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		getAccountDetailsByAccountNumber(accountNumber);
		accountRepository.deleteById(accountNumber);
		
		
	}

}
