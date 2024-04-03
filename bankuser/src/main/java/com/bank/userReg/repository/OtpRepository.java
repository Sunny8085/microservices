package com.bank.userReg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import com.bank.userReg.model.OneTimePassword;

@Repository
public interface OtpRepository extends MongoRepository<OneTimePassword, String>{
	//@Query("{'_id':?0},{'emailOtpStatus':?1,'mobileOtpStatus':?2}")
	@Query("{'_id':?0}")
	@Update("{$set:{'emailOtpStatus':?1,'mobileOtpStatus':?2}}")
	int updateOtpStatus(String userid,String emailstatus,String mobilestatus);
	
}
