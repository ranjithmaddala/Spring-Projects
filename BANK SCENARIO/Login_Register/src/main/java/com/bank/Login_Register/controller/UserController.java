package com.bank.Login_Register.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.Login_Register.Entity.UserEntity;
import com.bank.Login_Register.dto.LoginDTO;
import com.bank.Login_Register.dto.UserDTO;
import com.bank.Login_Register.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin
@RestController
@RequestMapping
@Validated
public class UserController {

	@Autowired
	UserService serv;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer( @RequestBody UserDTO user) throws InterruptedException{
		logger.warn(user.getAccountNumber());
		logger.warn(user.getEmailId());
		String res = serv.registerUser(user);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> loginUser(@Valid @RequestBody LoginDTO login){
		return new ResponseEntity<>(serv.loginUser(login),HttpStatus.OK);
	}
	
	@PatchMapping("/isVerified")
	public ResponseEntity<Boolean> isVerifiedUser(@RequestParam String email){
		return new ResponseEntity<Boolean>(serv.isVerified(email), HttpStatus.OK);
	}
	
	@GetMapping("/getuser")
	public ResponseEntity<UserEntity> getUser(@RequestParam String email) {
		return new ResponseEntity<UserEntity>(serv.getUser(email), HttpStatus.OK);
	}
	
	@GetMapping("/getuserbyid")
	public ResponseEntity<UserEntity> getUserById(@RequestParam String id) {
		return new ResponseEntity<UserEntity>(serv.getUserbyId(id), HttpStatus.OK);
	}
	
	@PatchMapping("/updateuser")
	public ResponseEntity<String> getUser(@RequestParam String email, @RequestBody UserDTO dto) {
		logger.warn(dto.getUserName());
		logger.warn(dto.getEmailId());
		return new ResponseEntity<String>(serv.updateUser(email, dto), HttpStatus.OK);
	}
	
	@GetMapping("/balance")
	public ResponseEntity<String> updateAccountBalance(@RequestParam String accountNumber, @RequestParam Double Amount){
		return new ResponseEntity<String>(serv.updateBalance(accountNumber, Amount), HttpStatus.OK);
	}
	
	@GetMapping("/updatesender")
	public ResponseEntity<String> updateSenderAccountBalance(@RequestParam String accountNumber, @RequestParam Double Amount){
					return new ResponseEntity<String>(serv.updateSenderBalance(accountNumber, Amount), HttpStatus.OK);
	}
	
	@GetMapping("/matches")
	public ResponseEntity<Boolean> matchPin(@RequestParam String num, @RequestParam String pin ){
		return new ResponseEntity<Boolean>(serv.verifyPin(num, pin), HttpStatus.OK);
	}
}
