package com.bank.bankcustomer.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="transaction_type")
public class TransactionType {
	
	@Id
	@Column(name="transaction_type_id")
	private BigInteger transactionTypeId;

	@Column(name="transaction_type_name",nullable = false)
	private String transactionTypeName;
	
	@Column(name="transaction_type_desc",nullable = false)
	private String transactionTypeDesc;
	
	@Column(name="transaction_amount",nullable = false)
	private BigDecimal transactionAmount;	
}










