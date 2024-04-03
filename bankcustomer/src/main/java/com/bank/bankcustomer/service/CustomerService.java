package com.bank.bankcustomer.service;

import com.bank.bankcustomer.dto.CustomerAccountDto;
import com.bank.bankcustomer.model.Customer;

public interface CustomerService {
	public Customer activateCustomer(CustomerAccountDto account);
}
