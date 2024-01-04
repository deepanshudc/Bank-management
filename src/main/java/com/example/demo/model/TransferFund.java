package com.example.demo.model;

import com.example.demo.entity.AccountType;
import com.example.demo.entity.Customer;

public class TransferFund {
	
	private int custId;
	private int fromAccountId;
	private int toAccountId;

	private long amount;
	
//	public TransferFund(int fromAccountId,String fromAccountIdAccType, int toAccountId, String toAccountIdAccType, long amount) {
//		super();
//		this.fromAccountId = fromAccountId;
//		this.fromAccountIdAccType=AccountType.valueOf(toAccountIdAccType);
//		this.toAccountId = toAccountId;
//		this.toAccountIdAccType=AccountType.valueOf(toAccountIdAccType);
//		this.amount = amount;
//	}
	public TransferFund() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransferFund(int custId, int fromAccountId, int toAccountId, long amount) {
		super();
		this.custId = custId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
	}
	
	
	
	public int getFromAccountId() {
		return fromAccountId;
	}
	
	public int getToAccountId() {
		return toAccountId;
	}
	
	public long getAmount() {
		return amount;
	}

	public int getCustId() {
		// TODO Auto-generated method stub
		return custId;
	}
		
		
	
	
	
	
//	public int getFromAccountId() {
//		return fromAccountId;
//	}
////	public void setFromAccountId(int fromAccountId) {
////		this.fromAccountId = fromAccountId;
////	}
//	public int getToAccountId() {
//		return toAccountId;
//	}
////	public void setToAccountId(int toAccountId) {
////		this.toAccountId = toAccountId;
////	}
//	public long getAmount() {
//		return amount;
//	}
////	public void setAmount(long amount) {
////		this.amount = amount;
////	}
//	public String getFromAccountIdAccType() {
//		return this.fromAccountIdAccType.toString();
//	}
//	public String getToAccountIdAccType() {
//		return this.toAccountIdAccType.toString();
//	}
//	
	
	

}
