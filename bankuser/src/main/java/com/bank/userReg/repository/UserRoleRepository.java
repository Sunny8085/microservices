package com.bank.userReg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.userReg.model.UserRole;

public interface UserRoleRepository extends MongoRepository<UserRole,String>{

}
