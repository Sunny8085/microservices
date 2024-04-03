package com.bank.userReg.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.userReg.dto.AccountDto;
import com.bank.userReg.exceptionhandler.JsonSerializationException;
import com.bank.userReg.exceptionhandler.ResourceFoundException;
import com.bank.userReg.exceptionhandler.UserNotFoundException;
import com.bank.userReg.model.AccountOpening;
import com.bank.userReg.repository.AccountRepository;
import com.bank.userReg.util.DataTransfer;
import com.bank.userReg.util.FileUploadHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private FileUploadHelper fileuploadhelper;
	@Autowired
	private DataTransfer dataTransfer;
	@Autowired
	private AccountRepository accountRepository; 
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public ResponseEntity<String> openAccount(AccountDto accountDto) {
		AccountOpening accountOpeningInsert = new AccountOpening();
		boolean flage = fileuploadhelper.uploadMultFile(accountDto);
		if(flage) {
			AccountOpening accountOpening = dataTransfer.accountDtoToModel(accountDto);
			accountOpeningInsert = accountRepository.insert(accountOpening);
		}else {
			throw new ResourceFoundException("Internal server error.");
		}
		return (accountOpeningInsert != null) ? 
				ResponseEntity.status(HttpStatus.OK).body("Recored inserted"+accountDto.getUserId()):
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Recored not Inserted"+accountDto.getUserId());
	}
	@Override
	public ResponseEntity<List<AccountOpening>> getAllAccount() {
	    List<AccountOpening> allDetails = accountRepository.findAll();
	    allDetails.forEach(account -> account.getUserRegistration()
	    		.setPassword(null));
		return (allDetails != null)? 
					ResponseEntity.status(HttpStatus.OK).body(allDetails):
					ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
	}
	@Override
	public ResponseEntity<String> activateAccount(AccountDto accountDto) {
		AccountOpening accountOpening = accountRepository
			    .findByUserRegistration_IdAndAccountType(accountDto.getUserId(), accountDto.getAccountType())
			    .orElseThrow(() -> new UserNotFoundException("Account form not found"));  
		accountOpening.setUserId(accountDto.getUserId());
		String accountOpeningJson;
		try {
			accountOpeningJson = objectMapper.writeValueAsString(accountOpening);
		}catch(Exception e) {
			throw new JsonSerializationException("Failed to serialize accountOpening to JSON", e);
		}	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(accountOpeningJson, headers);
		ResponseEntity<String> accountStatus = restTemplate.postForEntity("http://CUSTOMER-SERVICE/api/v1/coustomer/activate"
				, requestEntity, String.class);
		return accountStatus;
	}	
}





