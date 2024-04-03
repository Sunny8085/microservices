package com.bank.bankcustomer.serviceImpl;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bankcustomer.dto.CustomerAccountDto;
import com.bank.bankcustomer.model.Account;
import com.bank.bankcustomer.model.AccountStatusType;
import com.bank.bankcustomer.model.AccountType;
import com.bank.bankcustomer.model.Customer;
import com.bank.bankcustomer.model.InterestRate;
import com.bank.bankcustomer.repository.AccountRepository;
import com.bank.bankcustomer.repository.CustomerRepository;
import com.bank.bankcustomer.service.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	@Transactional
	public Customer activateCustomer(CustomerAccountDto account) {
		try {
			Account customerAccount = accountMapper(account);
			Account activeAccount = accountRepository.save(customerAccount);
			Customer customer = customerMapper(account,activeAccount);
			return customerRepository.save(customer);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Account accountMapper(CustomerAccountDto account) {
		Account customerAccount = Account.builder().currentBalance(BigDecimal.ZERO)
				.accountTypeId(account.getAccountType().equals("saving")?
						AccountType.builder().accountTypeId(BigInteger.ONE).build() : 
						AccountType.builder().accountTypeId(BigInteger.TWO).build())
				.accountStatusId(AccountStatusType.builder().accountStatusId("1").build())
				.interestRateId(InterestRate.builder().interestRateId(BigInteger.ONE).build()).build();
		return customerAccount;
	}
	public static Customer customerMapper(CustomerAccountDto account,Account customerAccount) throws ParseException {
		Customer customer = Customer.builder().account(new HashSet<>(Collections.singletonList(customerAccount)))
				.fname(account.getName())
				.state(account.getAddress().get(0).get("state"))
				.city(account.getAddress().get(0).get("city"))
				.pincode(new BigInteger(account.getAddress().get(0).get("pincode")))
				.address(account.getAddress().get(0).get("street"))
				.dob(new SimpleDateFormat("dd-MM-yyyy").parse(account.getDob()))
				.gender(account.getGender())
				.emailId(account.getEmail())
				.contactNo(new BigInteger(account.getContact()))
				.panNo(account.getPanNo())
				.adhaarNo(account.getAadhaarNo())
				.userLoginId(account.getUserId())
				.build();
		return customer;
	} 

}




