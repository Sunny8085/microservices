package com.bank.security.model;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bank.security.util.UserRegistrationSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users_details")
@JsonSerialize(using = UserRegistrationSerializer.class)
public class UserDetails{
		
		@Id
		private String id;
		
		private String fname;
		
		private String mname;
		
		private String lname;
		
		private String mobile;
		
		private String email;
		
		private String password;
		
		private String status;
		
		private LocalDate user_reg_date;
		
		@DBRef(lazy = true)
		@JsonBackReference
		private Set<UserRole> roles;

		public UserDetails(String id) {
			super();
			this.id = id;
		}

		public UserDetails(Set<UserRole> roles) {
			super();
			this.roles = roles;
		}

		
		
}
