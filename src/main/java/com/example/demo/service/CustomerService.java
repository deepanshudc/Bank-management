package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.TransferFund;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;

@Service 
public class CustomerService {
	
	@Autowired 
	private CustomerRepository repository;

	@Autowired
	private AccountRepository accountRepository;
	
	public List<Customer> getAllCustomer() {
		
		return (List<Customer>)repository.findAll();
	}


	public Customer getCustomer(int custId) throws Exception {
		// TODO Auto-generated method stub
		Customer customer=null;
		Optional<Customer>cust=repository.findById(custId);
		if(cust.isPresent()) {
			customer=cust.get();
		}
		if(customer==null) {
			throw new Exception("cusotmer with id"+custId+"not found");
		}
		return customer;
	}


	public Customer createCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		Customer saved= null;
		try {
			 saved=repository.save(customer);

		}
		catch(Exception e) {
			throw new Exception(e);
		}
		return saved;
		}


	public Customer updateCustomer(int custId, Customer customer) throws Exception {
		// TODO Auto-generated method stub
		Optional<Customer> found= repository.findById(custId);
		if(!found.isPresent()) {
			throw new Exception("Customer tihs id "+custId+"not found");
		}
		Customer updateCustomer=found.get();
		if(customer.getFirstName()!=null) {
			updateCustomer.setFirstName(customer.getFirstName());
		}
		if(customer.getLastName()!=null) {
			updateCustomer.setLastName(customer.getLastName());

		}
		if(customer.getEmail()!=null) {
			updateCustomer.setEmail(customer.getEmail());
		}
		
		repository.save(updateCustomer);
		
		return updateCustomer;
	}


	public String deleteCustomer(int custId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Customer> found= repository.findById(custId);
		if(!found.isPresent()) {
			throw new Exception("Customer this id "+custId+"not found");
		}
		
		repository.deleteById(custId);
		return new String("Customer with id:"+custId+" deleted");
	}


	public Customer addAccount(int custId, Account account) throws Exception {
		// TODO Auto-generated method stub
		Customer cust=null;
		if(!repository.findById(custId).isPresent()) {
			throw new Exception("Customer this id "+custId+"not found");
		}
		else {
			cust=repository.findById(custId).get();
			Account newAcc= new Account();
			newAcc.setAccountType(account.getAccountType());
			newAcc.setBalance(account.getBalance());
			newAcc.setCustomer(cust);
			List<Account> custAcccountList=cust.getAccounts();
			custAcccountList.add(newAcc);
			cust.setAccounts(custAcccountList);
			repository.save(cust);

		}
		return cust;
	}


	public String transferFunds(TransferFund info) {
		String result=null;
		// TODO Auto-generated method stub
		long amt=info.getAmount();
		int fromId=info.getFromAccountId();
		int toId=info.getToAccountId();
		int custId=info.getCustId();
		
		
		if(amt<0) {
			throw new NotFoundException("Amount to be transferred should be more than 0");
		}
		else if(fromId==toId) {
			throw new NotFoundException("Account FromId and ToId should be different");
		}
		else if(!repository.findById(custId).isPresent()) {
			throw new NotFoundException("Customer who is transfering fund with id: "+custId+" not found");
		}
		else if(!accountRepository.findById(toId).isPresent()) {
			throw new NotFoundException("Account Id: "+toId+" where amount need to be transfer not found");
		}
		else if(accountRepository.findById(toId).isPresent()) {
			Customer cust=repository.findById(custId).get();
			List<Account> accList=cust.getAccounts();
			boolean check =false;
			for(Account account:accList) {
				if(account.getAccountNo()==fromId) {
					check=true;
				}
			}
			if(check!=true) {
				throw new NotFoundException("Account Id: "+fromId+" where amount need to be transfer from Customer is not found");
			}
			
			Account fromAcc=accountRepository.findById(fromId).get();		
			Account toAcc=accountRepository.findById(toId).get();	
			fromAcc.setBalance(fromAcc.getBalance()-amt);
			toAcc.setBalance(toAcc.getBalance()+amt);
			accountRepository.save(toAcc);
			accountRepository.save(fromAcc);
			
			result= "Amount: "+amt+" transferred successfully from customer id: "+custId+" 's account no: "+fromId+" to account no: "+toId+".Customer balance is "+fromAcc.getBalance();
		}
		return result;
	}		
	
	
	
}
