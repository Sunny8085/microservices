package com.bank.userReg.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bank.userReg.dto.AccountDto;
import com.bank.userReg.model.AccountOpening;

public interface AccountService {
	
	public ResponseEntity<String> openAccount(AccountDto accountopening);
	
	public ResponseEntity<List<AccountOpening>> getAllAccount();
	
	public ResponseEntity<String> activateAccount(AccountDto accountDto);
}
