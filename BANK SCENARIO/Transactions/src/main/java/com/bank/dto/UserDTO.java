package com.bank.dto;

import lombok.Data;

@Data
public class UserDTO {
	
	private String accountNumber;
	
	private String loginPwd;
	
	private String transactionPwd;
	
	private String emailId;

	private String userName;
	
	private Boolean isVerified;

}
