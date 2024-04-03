package com.bank.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.security.config.CustomUserDetails;
import com.bank.security.repository.UserAuthenticationRepository;

@Service
@Primary
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserAuthenticationRepository userAuthRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.bank.security.model.UserDetails user = userAuthRepository.findById(username)
				.orElseThrow(()-> new RuntimeException("User not found !!"));
		return new CustomUserDetails(user);
	}

}


