package com.bank.bankcustomer.model;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="failed_transaction_log")
public class FailedTransactionLog {
	
	@Id
	@Column(name="failed_transaction_id")
	private BigInteger failedTransactionLog;
	
	@Column(name="failed_transaction_error_id",nullable = false)
	private BigInteger failedTransactionId;
	
	@Column(name="failed_transaction_error_time",nullable = false)
	private String failedTransactionErrorTime;
	
	@Column(name="failed_transaction_xml",nullable = false)
	private String failedTransactionJSON;
}






