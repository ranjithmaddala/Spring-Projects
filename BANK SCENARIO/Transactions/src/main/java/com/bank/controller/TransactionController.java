package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TransactionDTO;
import com.bank.entity.Transaction;
import com.bank.service.TransactionService;

import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping
@RestController
@Validated
public class TransactionController {

	@Autowired
	TransactionService serv;
	
	@PostMapping("/get")
	public ResponseEntity<String> sendMoney(@RequestParam String accNumber, @Valid @RequestBody TransactionDTO dto){
		return new ResponseEntity<String>(serv.addTransaction(accNumber, dto), HttpStatus.OK);
	}; 
	
	@GetMapping("/getTrans")
	public ResponseEntity<List<Transaction>> getAlltransactions(@RequestParam String accNumber){
		return new ResponseEntity<List<Transaction>>(serv.getAllTransactions(accNumber), HttpStatus.OK);
	}; 
	
	
}
