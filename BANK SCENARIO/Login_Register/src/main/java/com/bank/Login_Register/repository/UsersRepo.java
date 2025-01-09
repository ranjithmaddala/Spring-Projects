package com.bank.Login_Register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.Login_Register.Entity.UserEntity;
import java.util.Optional;
import java.util.List;



public interface UsersRepo extends JpaRepository<UserEntity, String> {
	Optional<UserEntity> findByEmailId(String emailId);
	
	boolean existsByEmailId(String emailId);
	
	List<UserEntity> findByUserName(String userName);
}
