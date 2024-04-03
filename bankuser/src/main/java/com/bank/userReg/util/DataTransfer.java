package com.bank.userReg.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.bank.userReg.dto.AccountDto;
import com.bank.userReg.model.AccountOpening;

@Component
public class DataTransfer {
	
	public AccountOpening accountDtoToModel(AccountDto accountDto) {
		AccountOpening accountOpening = new AccountOpening(accountDto.getUserId());
		accountOpening.setApplyDate(LocalDate.now()); 
		accountOpening.setName(accountDto.getName());
		accountOpening.setAccountType(accountDto.getAccountType());
		accountOpening.setAccountBranch(accountDto.getAccountBranch());
		accountOpening.setDob(accountDto.getDob());
		accountOpening.setGender(accountDto.getGender());
		accountOpening.setEmail(accountDto.getEmail());
		accountOpening.setContact(accountDto.getContact());
		accountOpening.setNationality(accountDto.getNationality());
		accountOpening.setSalary(accountDto.getSalary());
		accountOpening.setAddress(accountDto.getAddress());
		accountOpening.setNominee(accountDto.getNominee());
		accountOpening.setNomineeId(accountDto.getNomineeId());
		accountOpening.setNomineeProof(accountDto.getUserId()+"nominee.jpg");
		accountOpening.setPanNo(accountDto.getPanNo());
		accountOpening.setAadhaarNo(accountDto.getAadhaarNo());
		accountOpening.setPanImage(accountDto.getUserId()+"pancard.jpg");
		accountOpening.setAadhaarImage(accountDto.getUserId()+"aadhaarcard.jpg");
		accountOpening.setProfile(accountDto.getUserId()+"profile.jpg");
		accountOpening.setSignature(accountDto.getUserId()+"signature.jpg");
		accountOpening.setAccountStatus("0");
		return accountOpening;
	}
	
	
	
}
