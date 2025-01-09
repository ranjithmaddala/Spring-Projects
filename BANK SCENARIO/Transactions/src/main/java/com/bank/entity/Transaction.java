package com.bank.entity;

import java.time.LocalDateTime;

import com.bank.dto.TransactionDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	private String transactionId;
	
	private String accountNumber;
	
	private String recAccountNumber;
	
	private String recAccountName;

	private String recEmail;
	
	private Double amount;
	
	private String status;
	
	private LocalDateTime transactionDate;
}
