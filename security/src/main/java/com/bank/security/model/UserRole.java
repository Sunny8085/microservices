package com.bank.security.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document(collection="user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
	
	@Id
	private String id;
	
	@DBRef(lazy = true)
	private Roles roles;
	
	@DBRef(lazy = true)
	private UserDetails userDetails;

	public UserRole(String roleId, String userId) {
		super();
		this.roles = new Roles(roleId);
		this.userDetails = new UserDetails(userId);
	} 
}