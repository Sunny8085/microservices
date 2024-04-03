package com.bank.userReg.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.userReg.dto.UserDto;
import com.bank.userReg.service.UserService;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/reg")
public class RegistrationController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/adduser")
	public ResponseEntity<String> saveUser(@Validated @RequestBody UserDto user) {
		return userService.insertUser(user);
	}
	
	@GetMapping("/otpgen/{userid}/{email}/{mobile}")
	public ResponseEntity<String> saveOtp(@PathVariable String userid,@PathVariable String email,@PathVariable String mobile) {
		return userService.genrateOtp(userid,email,mobile);
	}
	
	@GetMapping("/verify/{userid}/{emailotp}/{mobileotp}")
	public ResponseEntity<String> verifyOtp(@PathVariable String userid,@PathVariable String emailotp,@PathVariable String mobileotp) {
		return userService.verifyOtp(userid,emailotp,mobileotp);
	}
	
	@GetMapping("/login/{userid}/{password}")
	public ResponseEntity<List<UserDto>> varifyUser(@PathVariable String userid,@PathVariable String password) {
		return userService.validUser(userid,password);
	}
	
	@PutMapping("/newpassword/{userid}/{newpassword}")
	public ResponseEntity<String> resetPassword(@PathVariable String userid,@PathVariable String password) {
		return userService.resetPassword(userid,password);
	}
	
	@GetMapping("/checkuser/{userid}")
	public ResponseEntity<String> checkUser(@PathVariable String userid) {
		return userService.checkUser(userid);
	}
	
	@PostMapping("/uploadfile")
	public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile file) throws IOException{
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());	
		System.out.println(file.getName());
		System.out.println(file.hashCode());
		System.out.println(file.getBytes());
		System.out.println(file.getInputStream());
		System.out.println(file.getResource());
		
		if(file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
		}else {
			boolean f = userService.upload(file);
			if(f) {
				return	ResponseEntity.ok("File is successfully uploaded");
			}else {
				return	ResponseEntity.ok("File is Not uploaded");
			}
		}
	}
	
}















