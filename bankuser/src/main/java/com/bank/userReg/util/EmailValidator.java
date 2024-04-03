package com.bank.userReg.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<CustomEmail, String>{
	
	@Override
    public void initialize(CustomEmail customEmail) {
    }
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null;
	}

}
