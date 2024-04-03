package com.bank.userReg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.userReg.dto.AccountDto;
import com.bank.userReg.model.AccountOpening;
import com.bank.userReg.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/account")
public class CustomerControllerReg {
	@Autowired
	AccountService accountService;
	
	@PostMapping("/")
	public ResponseEntity<String> accountOpening(@Valid @ModelAttribute AccountDto accountDto) {
		return accountService.openAccount(accountDto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<AccountOpening>> getAllAcount(){
		return accountService.getAllAccount();
	}
	
	@PostMapping("/activate")
	public ResponseEntity<String> activateAccount(@RequestBody AccountDto accountDto) {
	    ResponseEntity<String> status = accountService.activateAccount(accountDto);
	    HttpStatusCode httpStatus = status.getStatusCode();
	    return ResponseEntity.status(httpStatus).body(status.getBody());
	}
	
}
