package com.bank.userReg.util;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.bank.userReg.model.UserRegistration;
import com.bank.userReg.model.UserRole;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class UserRegistrationSerializer extends JsonSerializer<UserRegistration>{

	@Override
	public void serialize(UserRegistration value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeStartObject();
		gen.writeStringField("userId", value.getId());
		gen.writeStringField("fname",value.getFname());
		gen.writeStringField("mname", value.getMname());
		gen.writeStringField("lname", value.getLname());
		gen.writeStringField("mobile", value.getMobile());
		gen.writeStringField("email", value.getEmail());
		gen.writeStringField("password", value.getPassword());
		gen.writeStringField("status", value.getStatus());
		gen.writeStringField("userRegDate", value.getUser_reg_date().toString());
		
		
		gen.writeArrayFieldStart("roles");
		for (UserRole userRole : value.getRoles()) {
			gen.writeStartObject();
			gen.writeStringField("roleId", userRole.getRoles().getId());
			gen.writeStringField("roleName", userRole.getRoles().getRole_name());
			gen.writeEndObject();
        }
		gen.writeEndArray();
		gen.writeEndObject();
	}
	
}

	
	
	
	
	