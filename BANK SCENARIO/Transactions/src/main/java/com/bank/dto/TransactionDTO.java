package com.bank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDTO {
	
	private Double accountBalance;
	
	@NotNull
	private String recAccountNumber;
	
	@NotNull
	private String recAccountName;
	
	@NotNull
	private String recEmail;
	
	@NotNull
	private Double amount;
}
