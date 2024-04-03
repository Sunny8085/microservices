package com.bank.bankcustomer.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="transaction_log")
public class TransactionLog {
	
	@Id
	@Column(name="transaction_id")
	private BigInteger transactionId;
	
	@Column(name="tranction_date",nullable = false)
	private LocalDate transactionData;
	
	@Column(name="transaction_type_id",nullable = false)
	private BigInteger transactionTypeId;
	
	@Column(name="transaction_amount",nullable = false)
	private BigDecimal transactionAmount;
	
	@Column(name = "new_balance",nullable = false)
	private BigDecimal newBalance;
	
	@Column(name="account_id",nullable = false)
	private BigInteger accountId;
	
	@Column(name="cust_id",nullable = false)
	private BigInteger custId;
	
	@Column(name="employee_id",nullable = false)
	private String employeeId;
	
	@Column(name="userlogin_id",nullable = false)
	private String userLoginId;
	
}

















