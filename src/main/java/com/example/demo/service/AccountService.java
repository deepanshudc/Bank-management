package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.model.TransferFund;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository repository;

	public List<Account> getAllAccounts() {
		
		return (List<Account>) repository.findAll();
		// TODO Auto-generated method stub
	}

	public Account getAccount(int accountId) throws Exception {
		
		Account acc=null;
		
		Optional<Account> found=repository.findById(accountId);
		
		if(!found.isPresent())
		{
			throw new Exception("Account with account no : "+accountId+ " not found");
		}
		
		acc=found.get();
		// TODO Auto-generated method stub
		return acc;
	}

	public String transferFund(TransferFund info) throws Exception {
		// TODO Auto-generated method stub
		int accId1=info.getFromAccountId();
		int accId2=info.getToAccountId();
		if(accId1==accId2) {
			throw new Exception("fromAccountId and toAccountId should be different");
		}
		Account Acc1=null;
		Account Acc2=null;
		Optional<Account> acc1=repository.findById(accId1);
		Optional<Account> acc2=repository.findById(accId2);
		
		if(info.getAmount()==0) {
			throw new Exception("Amount should be more than 0");
		}
		
		
		if(!acc1.isPresent()) {
			throw new Exception("Account with account no : "+accId1+ " not found");
		}
		if(!acc2.isPresent()) {
			throw new Exception("Account with account no : "+accId2+ " not found");
		}

		Acc1=acc1.get();
		Acc2=acc2.get();
		if(Acc1.getBalance()<info.getAmount()) {
			throw new Exception("FromAccount balance is less than amount to be tranferred");
		}
		Acc1.setBalance(Acc1.getBalance()-info.getAmount());
		Acc2.setBalance(Acc2.getBalance()+info.getAmount());
		
		repository.save(Acc1);
		repository.save(Acc2);
		return "Amount successfully transferred from account :"+Acc1.getAccountNo()+"to account :"+Acc2.getAccountNo()+"\n"+"FromAccountId balance :"+Acc1.getBalance()+"and ToAccountId balance :"+Acc2.getBalance();
	}

	


}
