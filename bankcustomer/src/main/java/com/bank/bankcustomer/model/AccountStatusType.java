package com.bank.bankcustomer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "account_status_type")
public class AccountStatusType {
		
	@Id
	@Column(name="account_status_id")
	private String accountStatusId;
	
	@Column(name="account_staus_desc")
	private String accountStatusDesc;
}
