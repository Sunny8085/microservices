package com.bank.userReg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.userReg.model.Roles;

public interface RolesRepository extends MongoRepository<Roles, String>{

}
