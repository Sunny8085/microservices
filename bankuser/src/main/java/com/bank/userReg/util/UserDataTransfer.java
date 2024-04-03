package com.bank.userReg.util;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bank.userReg.dto.UserDto;
import com.bank.userReg.model.UserRegistration;
import com.bank.userReg.model.UserRole;
import com.bank.userReg.repository.UserRepository;
import com.bank.userReg.repository.UserRoleRepository;

@Component
public class UserDataTransfer {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private UserRepository userRepository;
	@Transactional
	public UserRegistration insertDataTransfer(UserDto userDto) {
		UserRegistration userReg = new UserRegistration();
		userReg.setId(userDto.getUserId());
		userReg.setFname(userDto.getFname());
		userReg.setMname(userDto.getMname());
		userReg.setLname(userDto.getLname());
		userReg.setMobile(userDto.getMobile());
		userReg.setEmail(userDto.getEmail());
		userReg.setStatus("N");
		userReg.setPassword(userDto.getPassword());
		userReg.setUser_reg_date(LocalDate.now());
		userRepository.insert(userReg);
		
		UserRole userRole = new UserRole("USERID002",userDto.getUserId());
        userRoleRepository.insert(userRole);
        
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(userRole);
		userReg.setRoles(userRoles);
		return userReg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}







