package com.bank.userReg.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.bank.userReg.model.AccountOpening;

@DataMongoTest
class AccountRepositoryTest {

	@Test
	List<AccountOpening> findAllAccount() {
		return null;
	}

}
