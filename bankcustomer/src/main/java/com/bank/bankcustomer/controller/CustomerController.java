package com.bank.bankcustomer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankcustomer.dto.CustomerAccountDto;
import com.bank.bankcustomer.model.Customer;
import com.bank.bankcustomer.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/coustomer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(value="/activate" , consumes = MediaType.APPLICATION_JSON_VALUE 
			, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> activateAccount(@RequestBody CustomerAccountDto account) {
		Customer customer = customerService.activateCustomer(account);
		if(customer != null)
			return ResponseEntity.status(HttpStatus.CREATED).body("Account Activated");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Internal Error");
	}
	
}











