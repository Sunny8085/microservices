package com.bank.bankcustomer.dto;

import java.util.Map;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUserDto {
	
	@NotBlank(message="UserId must not be blank")
	private String userId;
	
	@NotBlank(message="First name must not be blank")
	private String fname;
	
	private String mname;
	
	@NotBlank(message="Last name must not be blank")
	private String lname;
	
	@NotBlank(message="Mobile number must not be blank")
	private String mobile;
	
	@Email
	private String email;
	
	@NotBlank(message = "Password must not be blank")
	private String password;
	
	@NotBlank(message = "Role must not be  blank")
	private String role;
	
	private Set<Map<String,String>> roles;
}
