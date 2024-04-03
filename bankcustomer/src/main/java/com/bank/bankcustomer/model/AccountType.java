package com.bank.bankcustomer.model;

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
@Table(name ="account_type")
public class AccountType {
	
	@Id
	@Column(name="account_type_id",nullable = false)
	private BigInteger accountTypeId;
	
	@Column(name="account_type_desc",nullable = false)
	private String accountTypeDesc;
	
}
