package com.bank.userReg.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.bank.userReg.model.UserRegistration;

public interface UserRepository extends MongoRepository<UserRegistration,String>{
	
	@Query("{'_id':?0}")
	@Update("{$set:{'status':?1}}")
	int updateUserStatus(String userid,String userStatus);
	
	@Query(value="{'$and':[{'_id':?0},{'password':?1}]}",fields="{'_id':1,'fname':1,'fname':1,'lname':1,'mobile':1,"
			+ "'email':1,'status':1,'role':1}")
	Optional<UserRegistration> checkUser(String userid, String password);
	
	@Query("{'_id':?0}")
	@Update("{$set:{'password':?1}}")
	int resetPassword(String userid,String password);
	
	Optional<UserRegistration> findByIdAndPassword(String userid, String password);
}
