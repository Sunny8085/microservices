package com.bank.userReg.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bank.userReg.util.CustomEmail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDto {
	@NotNull(message="Name must be required")
	private String name;
	
	@NotNull(message="Account type must be required")
	private String accountType;
	
	@NotNull(message="Account branch must be required")
	private String accountBranch;
	
	@NotNull(message="Date of birth required")
	private String dob;
	
	@NotNull(message="Gender must be required")
	private String gender;
	
	@CustomEmail
	private String email;
	
	@NotNull(message="Mobile number must not be blank")
	@Pattern(regexp ="^[6-9]\\d{9}$",message="Mobile number must have exactly 10 digits and start with a digit from 6 to 9")
	private String contact;
	
	@NotNull(message="Nationality must be required")
	private String nationality;
	
	@NotNull(message="Salary must be requried")
	private String salary;
	
	@NotNull(message= "Address must be requried")
	private List<HashMap<String,String>> address= new ArrayList<HashMap<String,String>>();
	
	@NotNull(message="Nominee must be requried")
	private String nominee;
	
	@NotNull(message = "Nominee Id must be requried")
	private String nomineeId;
	
	@NotNull(message = "Pan No must be requried")
	@Size(min = 10, max = 10, message="Enter valid Pan Card number")
	private String panNo;
	
	@NotNull(message = "Aadhaar number must be requried")
	@Pattern(regexp = "^[0-9]{12}$",message="Enter valid Aadhaar number")
	private String aadhaarNo;
	
	@NotNull(message = "User id must be requried")
	private String userId;
	
	private MultipartFile nomineeProof;
	
	private MultipartFile panImage;
	
	private MultipartFile aadhaarImage;
	
	private MultipartFile profile;
	
	private MultipartFile signature;
}
