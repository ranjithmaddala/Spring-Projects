package com.bank.Login_Register.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
	
	@NotNull
	@NotBlank
	private String userId;
	@NotNull
	private String password;
}
