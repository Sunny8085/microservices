package com.bank.userReg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection="user_role")
public class UserRole {
	
	@Id
	private String id;
	
	@DBRef(lazy = true)
	private Roles roles;
	
	@DBRef(lazy = true)
	private UserRegistration userRegistration;

	public UserRole(String roleId, String userId) {
		super();
		this.roles = new Roles(roleId);
		this.userRegistration = new UserRegistration(userId);
	} 
}
