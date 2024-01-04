package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.model.TransferFund;
import com.example.demo.service.CustomerService;

import jakarta.annotation.PostConstruct;
import jakarta.websocket.server.PathParam;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	//GET
	@GetMapping(value = "/customer/getAllCustomer")
	public List<Customer> getAllCustomer(){
		List<Customer> list=service.getAllCustomer();
		return list;
	}
	
	
	@GetMapping(value = "/customer/getById/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id")  int custId) throws Exception{
		Customer customer=service.getCustomer(custId);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	//POST
	@PostMapping("/customer/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws Exception{
		Customer createdCustomer=service.createCustomer(customer);
		return new ResponseEntity<Customer>(createdCustomer,HttpStatus.CREATED);
		
	}
	
	//add account
	@PutMapping("/customer/addAccount/{id}")
	public ResponseEntity<Customer> addAccount(@PathVariable("id") int custId, @RequestBody Account account)throws Exception{
		Customer cust=service.addAccount(custId,account);
		return new ResponseEntity<Customer>(cust,HttpStatus.CREATED);

  	}
	
	//PUT
	@PutMapping("/customer/updateCustomer/{id}")
	public  ResponseEntity<Customer> updateCustomer(@PathVariable("id") int custId, @RequestBody Customer customer) throws Exception{
		
		Customer updated=service.updateCustomer(custId,customer);
		return new ResponseEntity<Customer>(updated,HttpStatus.OK);
		
	}
	
	
	//delete
	@DeleteMapping("/customer/deleteCustomer/{id}")
	public  ResponseEntity<String> deleteCustomer(@PathVariable("id") int custId) throws Exception{
		
		String deletedCust=service.deleteCustomer(custId);
		
		return new ResponseEntity<String>(deletedCust,HttpStatus.OK) ;
	
	}
	
	//transfer funds
	@PostMapping("/customer/transferFunds")
	public  ResponseEntity<String> transferFunds(@RequestBody TransferFund info) throws Exception{
		String result=service.transferFunds(info);
		return new ResponseEntity<String>(result,HttpStatus.OK) ;

	}
}

