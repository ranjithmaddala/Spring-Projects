package com.bank.Login_Register.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class UserEntity {
	
	@Id
	private String accountNumber;
	
	private String loginPwd;
	
	private String transactionPwd;
	
	@Column(name= "email_id", unique = true)
	private String emailId;

	private String userName;
	
	private Double accountBalance;
	
	private Boolean isVerified;
}