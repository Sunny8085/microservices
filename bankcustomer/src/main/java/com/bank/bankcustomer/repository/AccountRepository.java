package com.bank.bankcustomer.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bankcustomer.model.Account;

public interface AccountRepository extends JpaRepository<Account, BigInteger>{

}
