package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

import jakarta.annotation.Resource;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;
	
	//GET
	@GetMapping(value = "/account/getAllAccount")
	public List<Account> getAllAccounts(){
		List<Account> list=service.getAllAccounts();
		return list;
	}
	
	@GetMapping(value = "/account/findById/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable("id") int accountId) throws Exception{
		Account acc=service.getAccount(accountId);
		
		return new ResponseEntity<Account> (acc,HttpStatus.FOUND);
		
	}
	
}

