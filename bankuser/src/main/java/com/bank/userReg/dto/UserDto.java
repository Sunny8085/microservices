package com.bank.userReg.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bank.userReg.model.Roles;
import com.bank.userReg.model.UserRole;
import com.bank.userReg.util.CustomEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
	@NotBlank(message="UserId must not be blank")
	private String userId;
	
	@NotBlank(message="First name must not be blank")
	private String fname;
	
	private String mname;
	
	@NotBlank(message="Last name must not be blank")
	private String lname;
	
	@NotBlank(message="Mobile number must not be blank")
	private String mobile;
	
	@CustomEmail
	private String email;
	
	@NotBlank(message = "Password must not be blank")
	private String password;
	
	@NotBlank(message = "Role must not be  blank")
	private String role;
	
	private Set<Map<String,String>> roles;
	
	public UserDto() {
		super();
	}
	public UserDto(String id, String fname, String mname, String lname, String mobile, String email) {
		super();
		this.userId = id;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.mobile = mobile;
		this.email = email;
	}

}
