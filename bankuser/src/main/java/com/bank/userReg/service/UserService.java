package com.bank.userReg.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bank.userReg.dto.UserDto;
import com.bank.userReg.model.UserRegistration;

public interface UserService {
	
	public ResponseEntity<String> insertUser(UserDto user);
	
	public ResponseEntity<String> genrateOtp(String userid,String email,String mobile);
	
	public ResponseEntity<String> verifyOtp(String userid,String emailotp, String mobileotp);
	
	public ResponseEntity<List<UserDto>> validUser(String userid, String password);
	
	public ResponseEntity<String> resetPassword(String userid,String password);
	
	public ResponseEntity<String> checkUser(String userid);
	
	public boolean upload(MultipartFile file);
}
