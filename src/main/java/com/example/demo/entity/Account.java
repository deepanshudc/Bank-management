package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.Fetch;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accountNo;
	
	private long balance;
	
	private AccountType accountType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id")
    @JsonBackReference
	private Customer customer;

	
	public Account(int accountNo, long balance, String accountType, Customer customer) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.accountType = AccountType.valueOf(accountType);
		this.customer = customer;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		String accType=this.accountType.toString();
		return accType;
	}

	public void setAccountType(String accountType) {
		
		this.accountType = AccountType.valueOf(accountType);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getAccountNo() {
		return accountNo;
	}
	
	
	
	

}
