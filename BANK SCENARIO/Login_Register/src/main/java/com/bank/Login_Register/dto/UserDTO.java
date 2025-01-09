package com.bank.Login_Register.dto;

import com.bank.Login_Register.Entity.UserEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {
	
	@NotNull
	@NotBlank
	@Pattern(regexp="^\\d{12}$",message="Account number should contain only digits and of length 12.")
	private String accountNumber;
	
	@NotNull
	@NotBlank
	private String loginPwd;
	
	@NotNull
	@NotBlank
	private String cloginPwd;
	
	@NotNull
	@NotBlank
	private String transactionPwd;
	
	@NotNull
	@NotBlank
	private String ctransactionPwd;
	
	@NotNull
	@NotBlank
	private String emailId;

	@NotNull
	@NotBlank
	private String userName;
	
	private Double accountBalance;
	
	public static UserEntity dtoToEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		
		entity.setAccountNumber(dto.getAccountNumber());
		entity.setLoginPwd(dto.getLoginPwd());
		entity.setTransactionPwd(dto.getTransactionPwd());
		entity.setEmailId(dto.getEmailId());
		entity.setUserName(dto.getUserName());
		entity.setAccountBalance(dto.getAccountBalance());
		return entity;
	}
}
