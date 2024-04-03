package com.bank.bankcustomer.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name="account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
	@GenericGenerator(name="ACCOUNT_SEQ_ID",strategy = "com.bank.bankcustomer.generator.AccountIdGenerator")
	@GeneratedValue(generator = "ACCOUNT_SEQ_ID")
	@Column(name="account_id")
	private BigInteger AccountId;
	
	@Column(name="current_balance",nullable = false)
	private BigDecimal currentBalance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_type_id",referencedColumnName = "account_type_id")
	private AccountType accountTypeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_status_id",referencedColumnName="account_status_id")
	private AccountStatusType accountStatusId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="interest_rate_id",referencedColumnName="interest_rate_id")
	private InterestRate interestRateId;
	
	@ManyToMany(mappedBy = "account" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private final Set<Customer> customer = new HashSet<>();
	
}





















