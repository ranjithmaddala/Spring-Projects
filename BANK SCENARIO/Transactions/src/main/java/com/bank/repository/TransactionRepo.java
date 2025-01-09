package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Transaction;
import java.util.List;


public interface TransactionRepo extends JpaRepository<Transaction, String> {
	
	List<Transaction> findByRecAccountName(String recAccountName);
	
	List<Transaction> findByRecEmail(String recEmail);
	
	List<Transaction> findByAccountNumber(String accountNumber);

}
