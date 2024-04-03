package com.bank.userReg.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

@Constraint(validatedBy = EmailValidator.class)  
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.LOCAL_VARIABLE})
@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(?:gmail|outlook|yahoo)\\.(?:com)$",message="Invalid email format")
public @interface CustomEmail {
	String message() default "Invalid email format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
