package com.bank.userReg.model;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bank.userReg.util.UserRegistrationSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "users_details")
@JsonSerialize(using = UserRegistrationSerializer.class)
public class UserRegistration{
	@Id
	private String id;
	
	@NotBlank(message="First name must not be blank")
	private String fname;
	
	private String mname;
	
	@NotBlank(message="Last name must not be blank")
	private String lname;
	
	@NotBlank(message="Mobile number must not be blank")
	private String mobile;
	
	@NotBlank(message = "Email Id not be blank")
	private String email;
	
	@NotBlank(message="Password must not be blank")
	private String password;
	
	@NotBlank(message="Staus not be blank")
	private String status;
	
	@NotBlank(message = "User Registration not be blank")
	private LocalDate user_reg_date;
	
	@DBRef(lazy = true)
	@JsonBackReference
	private Set<UserRole> roles;

	public UserRegistration(String id) {
		super();
		this.id = id;
	}

	public UserRegistration(Set<UserRole> roles) {
		super();
		this.roles = roles;
	}	
	
}
