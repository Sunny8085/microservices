package com.bank.security.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bank.security.model.UserDetails;

public interface UserAuthenticationRepository extends MongoRepository<UserDetails, String>{
	
//	public Optional<UserDetails> findById(String email);
	@Query(value="{ 'id' : ?0 }")
    Map<String,String> findUsersByName(String userreg);
	
}
