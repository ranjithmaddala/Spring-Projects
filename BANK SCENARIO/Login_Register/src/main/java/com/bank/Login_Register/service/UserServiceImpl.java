package com.bank.Login_Register.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.Login_Register.Entity.EmailDetails;
import com.bank.Login_Register.Entity.UserEntity;
import com.bank.Login_Register.dto.LoginDTO;
import com.bank.Login_Register.dto.UserDTO;
import com.bank.Login_Register.repository.UsersRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepo repo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	RestTemplate template;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public String registerUser(UserDTO userDTO) throws InterruptedException {
		// TODO Auto-generated method stub
		logger.warn("Entered the registeration method");
		Boolean check = repo.existsById(userDTO.getAccountNumber());
		//List<UserEntity> user = repo.findByEmailId(userDTO.getEmailId()); 
		boolean user = repo.existsByEmailId(userDTO.getEmailId());
		
		
		
		if(!check && !user) {
			if(userDTO.getLoginPwd().equals(userDTO.getCloginPwd())){
				if(userDTO.getTransactionPwd().equals(userDTO.getCtransactionPwd())) {
					userDTO.setLoginPwd(encoder.encode(userDTO.getLoginPwd()));
					userDTO.setTransactionPwd(encoder.encode(userDTO.getTransactionPwd()));
					userDTO.setAccountBalance(50000.0);
					logger.warn("sending email ::: something");
					EmailDetails details = new EmailDetails();
					details.setUserEmail(userDTO.getEmailId());
					details.setUserName(userDTO.getUserName());
					template.postForObject("http://localhost:8085/register",details,String.class);
					logger.info("mail sent successfully!!!!!!!!!!");
					repo.save(UserDTO.dtoToEntity(userDTO));					
					return "We've sent the verification link, please check your email";	
				}
				else {
					return "Enetered Transaction password and confirm transaction password were not same";
				}
			}
			else {
				return "Enetered Login password and confirm login password were not same";
			}
		}
		return "registeration not-done!, please find the log";
	}

	@Override
	public Boolean loginUser(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		logger.warn("Entered the login method");
		Optional<UserEntity> user = null;
		if(repo.existsById(loginDTO.getUserId()) || repo.existsByEmailId(loginDTO.getUserId())) {
			logger.warn("found account "+loginDTO.getUserId());
			if(repo.existsById(loginDTO.getUserId()))
				user = repo.findById(loginDTO.getUserId());
			else
				user = repo.findByEmailId(loginDTO.getUserId());

			if(encoder.matches(loginDTO.getPassword(), user.get().getLoginPwd())) {
				logger.warn("Encoded password : "+encoder.matches(loginDTO.getPassword(), user.get().getLoginPwd()));
				return true;
			}
			logger.info("Password Mismatch");
			
			return false;
		}
		logger.info("unable to find the Account");
		return false;
	}

	@Override
	public Boolean isVerified(String email) {
		logger.warn("validating User Email");
		Optional<UserEntity> userr = repo.findByEmailId(email);
        userr.get().setIsVerified(template.getForObject("http://localhost:8085/isenabled?email="+email,boolean.class));
		repo.save(userr.get());
		return userr.get().getIsVerified();
	}

	@Override
	public UserEntity getUser(String email) {
		// TODO Auto-generated method stub
		logger.warn("Getting account of Email : " +email);
		if(repo.existsByEmailId(email)){
			UserEntity ent = new UserEntity();
			Optional<UserEntity> userr = repo.findByEmailId(email);
			
			ent.setAccountNumber(userr.get().getAccountNumber());
			ent.setEmailId(email);
			ent.setUserName(userr.get().getUserName());
			isVerified(email);
			ent.setIsVerified(userr.get().getIsVerified());
			ent.setAccountBalance(userr.get().getAccountBalance());
			ent.setTransactionPwd(userr.get().getTransactionPwd());
			return ent;
		}
		throw new RuntimeException("No Account Found with given email");
	}

	@Override
	public String updateUser(String email, UserDTO dto) {
		// TODO Auto-generated method stub
		
		logger.warn("updating user calling ===> "+dto.getEmailId(), dto.getUserName());
		if(repo.existsByEmailId(email)) {
			Optional<UserEntity> userr = repo.findByEmailId(email);
			if(dto.getUserName() != null)
				userr.get().setUserName(dto.getUserName());
			if(dto.getEmailId().equals(email))
				userr.get().setEmailId(email);
			else
			if(dto.getEmailId() != null) {
				userr.get().setEmailId(dto.getEmailId());
				EmailDetails details = new EmailDetails();
				details.setUserEmail(dto.getEmailId());
				details.setUserName(dto.getUserName());
				logger.warn("attempting to send email to --> "+dto.getEmailId());
				template.postForObject("http://localhost:8085/register",details,String.class);
				logger.info("mail sent successfully!!!!!!!!!!");
			}
				
			repo.save(userr.get());
			return "Successfully Upated!!";
		}
		return null;
	}

	@Override
	public String updateBalance(String accountNumber, Double amount) {
		// TODO Auto-generated method stub
		Optional<UserEntity> user = repo.findById(accountNumber);
		
		if(user.get().getAccountBalance()>=amount) {
			user.get().setAccountBalance(user.get().getAccountBalance() - amount);
			repo.save(user.get());
			return "Current Balance : "+user.get().getAccountBalance() ;
		}
			
		return "Current Balance: " +user.get().getAccountBalance();
	}

	@Override
	public UserEntity getUserbyId(String id) {
		return repo.findById(id).get();
	}

	@Override
	public String updateSenderBalance(String accountNumber, Double amount) {
		Optional<UserEntity> us = repo.findById(accountNumber);
		if(amount>0) {
			us.get().setAccountBalance(us.get().getAccountBalance()+amount);
			repo.save(us.get());
			return "Sending "+amount+" Completed to the account :"+accountNumber;
		}
		return "please enter amount more than 0";
	}

	@Override
	public Boolean verifyPin(String num, String pin) {
		// TODO Auto-generated method stub
		
		UserEntity userrrr = getUserbyId(num);
		
		if( encoder.matches(pin, userrrr.getTransactionPwd()))
			return true;
		return false;
	}

}
