package com.bank.Login_Register.service;

import com.bank.Login_Register.Entity.UserEntity;
import com.bank.Login_Register.dto.LoginDTO;
import com.bank.Login_Register.dto.UserDTO;

public interface UserService {
	
	public String registerUser(UserDTO userDTO) throws InterruptedException;
	
	public Boolean loginUser(LoginDTO loginDTO);
	
	public Boolean isVerified(String email);
	
	public UserEntity getUser(String email);
	
	public String updateUser(String email, UserDTO dto);
	
	public String updateBalance(String accountNumber, Double amount);

	public UserEntity getUserbyId(String id);

	public String updateSenderBalance(String accountNumber, Double amount);
	
	public Boolean verifyPin(String num, String pin);
}
