package com.bank.service;

import java.util.List;

import com.bank.entity.Account;

public interface AccountService {

	public Account createAccount(Account account);
	public Account getAccountDetailsByAccountNumber(Long accuntNumber);
	public List<Account> getAllAccountDetails();
	public Account depositAmount(Long accountNumber, Double amount);
	public Account withDrawAmount(Long accountNumber, Double amount);
	public void closeAccount(Long accountNumber);
}
