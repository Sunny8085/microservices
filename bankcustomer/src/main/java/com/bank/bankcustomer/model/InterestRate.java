package com.bank.bankcustomer.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name="interest_rate")
public class InterestRate {
	
	@Id
	@Column(name="interest_rate_id")
	private BigInteger interestRateId;
	
	@Column(name="interest_rate_value",nullable = false)
	private BigDecimal interestRateValue;
	
	@Column(name="interest_rate_desc",nullable = false)
	private String interestRatedesc;
}






