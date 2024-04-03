package com.bank.userReg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bank.userReg.model.AccountOpening;

public interface AccountRepository extends MongoRepository<AccountOpening,String>{
	
	@Query("{'account_status':'0'}")
	List<AccountOpening> findAllAccount();

	Optional<AccountOpening> findByUserRegistration_IdAndAccountType(String userId, String accountType);
	
}
