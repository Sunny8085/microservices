package com.bank.userReg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "roles")
public class Roles {
	@Id
	private String id;
	
	private String role_name;

	public Roles(String roleId) {
		super();
		this.id = roleId;
	}
}
