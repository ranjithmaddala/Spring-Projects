package com.bank.Login_Register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAspectJAutoProxy
public class LoginRegisterApplication{

	public static void main(String[] args) {
		SpringApplication.run(LoginRegisterApplication.class, args);
	}
	
	 @Bean
	 public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(10);
	 }   
	
}
