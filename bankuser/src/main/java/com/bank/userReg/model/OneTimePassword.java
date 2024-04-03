package com.bank.userReg.model;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@Document(collection = "otp_status")
public class OneTimePassword {
	@Id
	@NotNull(message="User id must be required")
	private String id;
	@NotNull(message = "Mobile number not be blank")
	private String mobile;
	private String mobileOtp;
	private String mobileOtpStatus;
	@NotNull(message = "Email id not be blank")
	private String email;
	private String emailOtp;
	private String emailOtpStatus;
	@NotNull(message ="Otp date not be blank")
	private LocalDate otp_date;
}
