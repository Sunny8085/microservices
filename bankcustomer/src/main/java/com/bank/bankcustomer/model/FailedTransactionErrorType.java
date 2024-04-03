package com.bank.bankcustomer.model;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="failed_transaction_error_type")
public class FailedTransactionErrorType {
	
	@Id
	@Column(name = "failed_transaction_error_id")
	private BigInteger failedTransactionErrorId;
	
	@Column(name = "failed_transaction_desc")
	private String failedTransactionDesc;
}
