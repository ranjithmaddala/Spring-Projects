package com.bank.service;

import java.util.List;

import com.bank.dto.TransactionDTO;
import com.bank.entity.Transaction;

public interface TransactionService { 
	
	public List<Transaction> getAllTransactions(String accountNumber);
	
	public String addTransaction(String senderAccountNumber, TransactionDTO dto);
}
