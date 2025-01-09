package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.dto.TransactionDTO;
import com.bank.entity.Transaction;
import com.bank.repository.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionRepo repo;
	
	@Autowired
	RestTemplate temp;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public String generateTransactionId() {
		Random random = new Random();
        long randomId = (long) (random.nextDouble() * Math.pow(10, 16));
        String uniqueId = String.format("%016d", randomId); // Ensure it's 16 digits with leading zeros
        System.out.println("Generated Unique ID: " + uniqueId);
		return uniqueId;
	}
	
	public String sendMoney(String senderAccountNumber, TransactionDTO dto) {
		
		String senderMsg="";
		
		logger.warn("Entered sending money from : "+dto.getRecAccountNumber() );

		String ue = temp.getForObject("http://localhost:1234/abcbanking/getuserbyid?id="+senderAccountNumber, String.class);
		
		String[] uearr = ue.split(",");
		
		String acc = uearr[0].split(":")[1];
		
		Double balance = Double.valueOf(uearr[uearr.length-2].split(":")[1]);
		
		Boolean ver = uearr[uearr.length-1].split(":")[1].equals("true}") ? true : false;
		
		logger.warn("Balance " +balance);
		logger.warn(uearr[uearr.length-1].split(":")[1] + "is verified :  " +ver);
		
		acc = acc.replaceAll("\"", "");
		
		logger.warn(acc+" ---> "+acc.equals(dto.getRecAccountNumber()));
		
		String ue1 = temp.getForObject("http://localhost:1234/abcbanking/getuserbyid?id="+dto.getRecAccountNumber(), String.class);
		
		logger.warn(ue1);
		
		String[] uearr1 = ue1.split(",");
		
		String email = uearr1[3].split(":")[1];
		
		email = email.replaceAll("\"", "");
		
		logger.warn("Email --> "+email);
		
		
		String msg = "";
		
		//String senderMsg = temp.getForObject("http://localhost:1234/abcbanking/updatesender?accountNumber="+dto.getRecAccountNumber()+"&Amount="+dto.getAmount(), String.class);
		
		if(ue != null) {
			logger.warn(senderAccountNumber +" ---> "+dto.getRecAccountNumber());
			if(!senderAccountNumber.equals(dto.getRecAccountNumber())) {
				if(ver) {
					if(dto.getRecEmail().equals(email)) {
						if( dto.getAmount() <= balance) {
							if(dto.getAmount()==0)
								return "Amount must be More than 0";
							msg = temp.getForObject("http://localhost:1234/abcbanking/balance?accountNumber="+senderAccountNumber+"&Amount="+dto.getAmount(), String.class);
							senderMsg = temp.getForObject("http://localhost:1234/abcbanking/updatesender?accountNumber="+dto.getRecAccountNumber()+"&Amount="+dto.getAmount(), String.class);
							return "Transaction Success \n " + senderMsg +"\n Your" + msg;
						}
						return "Sorry, Transaction failed due to insufficient balance";
					}
					return "Transaction failed due to mismatch in sender account details, Please check the sender details";
				}
				return "Sorry Failed, You're not supposed to do transaction as you haven't verified your identity!!!";
			}
		    return "failed, sender and reciever accounts should not be same";
		}		
		return "Oops failed, Something Went Wrong!";
	}

	@Override
	public String addTransaction(String senderAccountNumber, TransactionDTO dto) {
		Transaction tran = new Transaction();
		tran.setTransactionId(generateTransactionId());
		String st= sendMoney(senderAccountNumber, dto);
		if(st.contains("failed"))
			tran.setStatus("Failed");
		else
			tran.setStatus("Success");
		tran.setAccountNumber(senderAccountNumber);
		tran.setRecAccountNumber(dto.getRecAccountNumber());
		tran.setRecAccountName(dto.getRecAccountName());
		tran.setRecEmail(dto.getRecEmail());
		tran.setAmount(dto.getAmount());
		tran.setTransactionDate(LocalDateTime.now());
		repo.save(tran);
		return tran.getStatus().equals("Success") ?  "Transaction Completed successfully with ID : "+tran.getTransactionId() : "Transaction Failed";
	}

	@Override
	public List<Transaction> getAllTransactions(String accountNumber) {
		// TODO Auto-generated method stub
		return repo.findByAccountNumber(accountNumber);
	}
	
	
	
	
}
