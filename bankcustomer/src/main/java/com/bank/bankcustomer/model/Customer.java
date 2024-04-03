package com.bank.bankcustomer.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class Customer {
	@Id
	@GenericGenerator(name = "CUSTOMER_ID_SEQ", strategy = "com.bank.bankcustomer.generator.CustomerIdGenerator")
	@GeneratedValue(generator = "CUSTOMER_ID_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(name = "cust_id",precision = 15)
	private BigInteger customerId;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="customer_account",
		joinColumns = @JoinColumn(name="customer_cust_id",referencedColumnName = "cust_id"),
		inverseJoinColumns = @JoinColumn(name="account_account_id",referencedColumnName = "account_id"))
	@Column(name = "account_id")
	private Set<Account> account;
	
	@Column(nullable = false)
	private String fname;
	
	private String mname;
	
	private String lname;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private BigInteger pincode;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private Date dob;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(name="email",nullable = false)
	private String emailId;
	
	@Column(name="phone",nullable = false)
	private BigInteger contactNo;
	
	@Column(name="pan",nullable = false)
	private String panNo;
	
	@Column(name="adhaar",nullable = false)
	private String adhaarNo;
	
	@Column(name="userlogin_id",nullable = false)
	private String userLoginId;

}





